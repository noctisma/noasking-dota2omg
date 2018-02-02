package com.noasking.dota2.web.service;

import com.noasking.dota2.entity.AbilityEntity;
import com.noasking.dota2.repository.AbilityRepository;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析Dota2 Beta游戏中的npc_abilities.txt文件来获取最新的技能名称和ID的映射关系
 * Created by MaJing on 2018/1/31.
 */
@Service
public class AbilityParseService {

    private static final Logger logger = LoggerFactory.getLogger(AbilityParseService.class);

    @Autowired
    private AbilityRepository abilityRepository;

    private static final String STR_1 = "\t{";
    private static final String STR_2 = "\t\t// General";
    private static final String STR_3 =
            "\t\t//-------------------------------------------------------------------------------------------------------------";
    private static final String STR_4 = "\t\t\"ID\"";

    /**
     * 解析技能文件
     *
     * @param str1
     * @throws IOException
     */
    public void execute(String str1) throws IOException {
        logger.info("解析技能文件开始!");
        List<String> strArray = IOUtils.readLines(IOUtils.toInputStream(str1));
        Map<Integer, String> abilities = new HashMap<>();
        for (int i = 0; i < strArray.size(); i++) {
            String str = strArray.get(i);
            if (str.startsWith("\t\"")) { // 如果行字符串以:制表符"开头
                if (STR_1.equals(strArray.get(i + 1)) && STR_2.equals(strArray.get(i + 2)) && STR_3.equals(strArray
                        .get(i + 3)) && strArray.get(i + 4).startsWith(STR_4)) {
                    String idStr = strArray.get(i + 4);
                    idStr = idStr.substring(6);
                    System.out.println();
                    abilities.put(Integer.parseInt(idStr.substring(idStr.indexOf("\"") + 1, idStr.lastIndexOf("\"")))
                            , str.substring(2, str.lastIndexOf("\"")));
                    i = i + 5;
                }
            }
        }
        // 解析完成开始入库，如果已经存在，判断英文名是否相同，相同则忽略，不同则修改保存，不存在则新增
        for (Map.Entry<Integer, String> entry : abilities.entrySet()) {
            AbilityEntity abilityEntity = abilityRepository.findOne(entry.getKey());
            if (abilityEntity == null) {
                abilityEntity = new AbilityEntity();
                abilityEntity.setId(entry.getKey());
                abilityEntity.setName(entry.getValue());
                abilityRepository.save(abilityEntity);
            } else {
                if (!entry.getValue().equals(abilityEntity.getName())) {
                    abilityEntity.setName(entry.getValue());
                    abilityRepository.save(abilityEntity);
                }
            }
        }
        logger.info("解析技能文件结束!");
    }

}
