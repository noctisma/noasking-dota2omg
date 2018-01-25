package com.noasking.dota2.consumer;

import com.noasking.dota2.consumer.model.hive.AbsHiveModel;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;
import java.util.UUID;

/**
 * 这里使用textfile进行写
 * Created by MaJing on 2017/12/20.
 */
@Component
public class HiveSave {

    private static final Logger logger = LoggerFactory.getLogger(HiveSave.class);

    private final String filename;

    public HiveSave() {
        // 初始化的时候给予一个文件名，同一个目录多个实例支持并发写，但是实例不支持并发
        filename = UUID.randomUUID().toString();
    }

    public <T> void batchSave(String filename, Class<T> clazz, List<T> values) {
//        Configuration conf = new Configuration();
//        Path path = new Path(filename);
//        WriterOptions opts = OrcFile.writerOptions(conf);
//        ObjectInspector inspector = ObjectInspectorFactory.getReflectionObjectInspector(clazz, ObjectInspectorFactory
//                .ObjectInspectorOptions.JAVA);
//        opts.inspector(inspector);
//        Writer writer = null;
//        try {
//            writer = OrcFile.createWriter(path, opts);
//            for (T value : values) {
//                writer.addRow(value);
//            }
//        } catch (IOException e) {
//            logger.error("Orc文件保存失败：" + filename, e);
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                writer.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public <T extends AbsHiveModel> void batchSaveTextfile(String pathStr, List<T> values) throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = null;
        OutputStream out = null;
        try {
            fs = FileSystem.get(URI.create(pathStr), conf);
            Path f = new Path(pathStr + "/" + filename);
            if (!fs.exists(f)) {
                out = fs.create(f, true);
            } else {
                out = fs.append(f);
            }
            StringBuffer sb = new StringBuffer();
            for (AbsHiveModel abs : values) {
                sb.append(abs.getAppendString()).append('\n');
            }
            IOUtils.copyBytes(new ByteArrayInputStream(sb.toString().getBytes()), out, 4096, true);
        } catch (IOException e) {
            throw e;
        } finally {
            out.close();
        }
    }

    /**
     * 容器销毁时执行的操作：把缓存的数据全部提交
     */
    @PreDestroy
    public void destory() {

    }


}
