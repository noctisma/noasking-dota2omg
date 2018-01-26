package com.noasking.dota2.web.utils.hive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.*;

/**
 * Created by MaJing on 2017/9/11.
 */
@Component
public class HiveJdbcUtil {

    private final Logger logger = LoggerFactory.getLogger(HiveJdbcUtil.class);

    @Autowired
    private HiveJdbcProperties hiveJdbcProperties;

    @PostConstruct
    public void init() throws ClassNotFoundException {
        Class.forName(hiveJdbcProperties.getDriver());
    }

    private Random random = new Random();

    /**
     * 获取Hive Thrift连接,如果当前URL不可用,轮询所有URL找到可用连接
     *
     * @return
     * @throws Exception
     */
    public Connection getConnection() throws Exception {
        Connection conn = null;
        for (int i = 0; i < hiveJdbcProperties.getUrls().size(); i++) {
            try {
                conn = DriverManager.getConnection(hiveJdbcProperties.getUrls().get(random.nextInt(hiveJdbcProperties
                                .getUrls().size())),
                        hiveJdbcProperties.getUsername(), hiveJdbcProperties.getPassword());
                return conn;
            } catch (Exception e) {
                logger.error("获取Hive Jdbc连接失败", e);
                if (i + 1 == hiveJdbcProperties.getUrls().size()) {
                    throw e;
                }
            }
        }
        return conn;
    }

    private void closeConnection(Connection conn) {
        if (conn == null)
            return;
        try {
            if (!conn.isClosed()) {
                // 关闭数据库连接
                conn.close();
            }
        } catch (SQLException e) {
            logger.error("#ERROR-HiveJdbc# :关闭数据库连接发生异常，请检查！", e);
        }
    }

    /**
     * 查询sql语句,返回键值类型
     *
     * @param sql
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> querySqlForKeyValue(String sql)
            throws Exception {
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                Map<String, Object> map = new HashMap();
                for (int i = 1; i <= columnCount; i++) {
                    map.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(map);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.error("查询sql失败:" + sql, ex);
            throw new SQLException("查询sql失败:[" + sql + "]" + ex.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                closeConnection(con);
            }
        }
        return list;
    }

    /**
     * 执行sql语句
     *
     * @param sql
     * @return
     * @throws Exception
     */
    public boolean excuteSql(String sql) throws Exception {
        Connection con = getConnection();
        boolean flag = false;
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            flag = stmt.execute(sql);
        } catch (SQLException e) {
            logger.error("执行sql失败:" + sql, e);
            throw new SQLException("执行sql失败:" + sql);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                closeConnection(con);
            }
        }
        return true;
    }

    /**
     * 查询sql语句,返回List<String[]>
     * <p>
     * String[]为一列数据
     *
     * @param sql
     * @return
     * @throws Exception
     */
    public List<String[]> queryRows(String sql) throws Exception {
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        List<String[]> list = new ArrayList<String[]>();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                String[] row = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    row[i] = String.valueOf(rs.getObject(i + 1));
                }
                list.add(row);
            }
            logger.info("表列信息:" + list.toString());
        } catch (SQLException ex) {
            logger.error("查询sql失败:" + sql, ex);
            throw new SQLException("查询sql失败:" + sql);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                closeConnection(con);
            }
        }
        return list;
    }

    /**
     * 执行多条sql语句
     *
     * @param sqls
     * @return
     * @throws Exception
     */
    public boolean excuteMultSql(String[] sqls) throws Exception {
        Connection con = getConnection();
        boolean flag = false;
        Statement stmt = null;
        String sqlTemp = null;
        try {
            stmt = con.createStatement();
            if (sqls != null) {
                for (String sql : sqls) {
                    sqlTemp = sql;
                    stmt.execute(sqlTemp);
                }
                flag = true;
            }
        } catch (SQLException e) {
            logger.error("执行sql失败:" + sqlTemp, e);
            throw new SQLException("执行sql失败:" + sqlTemp);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                closeConnection(con);
            }
        }
        return flag;
    }

}

