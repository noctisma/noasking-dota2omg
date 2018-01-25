package com.noasking.dota2.consumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.noasking.dota2.consumer.HiveSave;
import com.noasking.dota2.consumer.model.api.AbilityUpgrade;
import com.noasking.dota2.consumer.model.api.Match;
import com.noasking.dota2.consumer.model.api.Player;
import com.noasking.dota2.consumer.model.hive.HiveAbility;
import com.noasking.dota2.consumer.model.hive.HiveErrorRecord;
import com.noasking.dota2.consumer.model.hive.HiveMatch;
import com.noasking.dota2.consumer.model.hive.HivePlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 消息消费者
 * Created by MaJing on 2017/12/1.
 */
@Component
public class Dota2Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Dota2Consumer.class);

    @Autowired
    private HiveSave hiveOrcSave;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    private static Map<String, HiveSave> saveMap = Maps.newConcurrentMap();

    public Dota2Consumer() {
        saveMap.put("1", new HiveSave());
        saveMap.put("2", new HiveSave());
        saveMap.put("3", new HiveSave());
        saveMap.put("4", new HiveSave());
        saveMap.put("5", new HiveSave());
        saveMap.put("0", new HiveSave());
    }

    /**
     * Kafka消费者1
     *
     * @param content
     */
    @KafkaListener(topicPartitions = {@TopicPartition(partitions = {"0"}, topic = "d-topic")})
    public void listen(String content) throws IOException {
        execute(content, saveMap.get("0"));
    }

    @KafkaListener(topicPartitions = {@TopicPartition(partitions = {"1"}, topic = "d-topic")})
    public void listen1(String content) throws IOException {
        execute(content, saveMap.get("1"));
    }

    @KafkaListener(topicPartitions = {@TopicPartition(partitions = {"2"}, topic = "d-topic")})
    public void listen2(String content) throws IOException {
        execute(content, saveMap.get("2"));
    }

    @KafkaListener(topicPartitions = {@TopicPartition(partitions = {"3"}, topic = "d-topic")})
    public void listen3(String content) throws IOException {
        execute(content, saveMap.get("3"));
    }

    @KafkaListener(topicPartitions = {@TopicPartition(partitions = {"4"}, topic = "d-topic")})
    public void listen4(String content) throws IOException {
        execute(content, saveMap.get("4"));
    }

    @KafkaListener(topicPartitions = {@TopicPartition(partitions = {"5"}, topic = "d-topic")})
    public void listen5(String content) throws IOException {
        execute(content, saveMap.get("5"));
    }


    private void execute(String content, HiveSave hiveSave) throws IOException {
        List<HiveMatch> hiveMatchList = new ArrayList<>();
        List<HiveAbility> hiveAbilityList = new ArrayList<>();
        List<HivePlayer> hivePlayerList = new ArrayList<>();
        List<HiveErrorRecord> errorRecords = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        String dt = null;
        try {
            Match match = mapper.readValue(content, Match.class);
            HiveMatch matchDetailEntity = new HiveMatch();
            BeanUtils.copyProperties(match, matchDetailEntity);
            logger.info(String.valueOf(matchDetailEntity.getMatch_seq_num()));
            dt = sdf.format(new Date(matchDetailEntity.getStart_time() * 1000));
            List<Player> playerList = match.getPlayers();
            if (playerList != null && playerList.size() != 0) {
                for (Player player : playerList) {
                    HivePlayer playerEntity = new HivePlayer();
                    BeanUtils.copyProperties(player, playerEntity);
                    playerEntity.setMatch_id(match.getMatch_id());
                    hivePlayerList.add(playerEntity);
                    List<AbilityUpgrade> abilityUpgradeList = player.getAbility_upgrades();
                    if (abilityUpgradeList != null && abilityUpgradeList.size() != 0) {
                        for (AbilityUpgrade abilityUpgrade : abilityUpgradeList) {
                            HiveAbility abilityUpgradeEntity = new HiveAbility();
                            BeanUtils.copyProperties(abilityUpgrade, abilityUpgradeEntity);
                            abilityUpgradeEntity.setAccount_id(player.getAccount_id());
                            abilityUpgradeEntity.setMatch_id(match.getMatch_id());
                            hiveAbilityList.add(abilityUpgradeEntity);
                        }
                    }
                }
            }
            hiveMatchList.add(matchDetailEntity);
        } catch (IOException e) {
            logger.error("消费者消息解析失败!", e);
            HiveErrorRecord hiveErrorRecord = new HiveErrorRecord();
            hiveErrorRecord.setErrorMsg(e.getMessage());
            hiveErrorRecord.setRecord(content);
            errorRecords.add(hiveErrorRecord);
            hiveSave.batchSaveTextfile("hdfs://10.10.10.21/apps/hive/warehouse/d_error_record/dt=" + sdf.format
                    (new Date()), errorRecords);
        }
        hiveSave.batchSaveTextfile("hdfs://10.10.10.21/apps/hive/warehouse/d_match/dt=" + dt, hiveMatchList);
        hiveSave.batchSaveTextfile("hdfs://10.10.10.21/apps/hive/warehouse/d_player/dt=" + dt, hivePlayerList);
        hiveSave.batchSaveTextfile("hdfs://10.10.10.21/apps/hive/warehouse/d_ability/dt=" + dt, hiveAbilityList);
    }


}
