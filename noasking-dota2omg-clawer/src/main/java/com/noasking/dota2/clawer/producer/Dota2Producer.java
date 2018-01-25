package com.noasking.dota2.clawer.producer;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by MaJing on 20171217121212/12/1.
 */
@Component
public class Dota2Producer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static int a = 200000000;

    private Random random = new Random();

    public void send() {
        //       kafkaTemplate.send("sampleinput2", a++ + ",discription,201712171212121220101010");
        //kafkaTemplate.send("sampleinput2", a++ + ",discription,201712171212121220101010,3000");
        kafkaTemplate.send("d-topic", "{\"players\":[{\"account_id\":4294967295,\"player_slot\":0," +
                "\"hero_id\":40,\"item_0\":263,\"item_1\":50,\"item_2\":139,\"item_3\":65,\"item_4\":212," +
                "\"item_5\":116,\"backpack_0\":0,\"backpack_1\":46,\"backpack_2\":0,\"kills\":18,\"deaths\":6," +
                "\"assists\":17,\"leaver_status\":0,\"last_hits\":180,\"denies\":4,\"gold_per_min\":606," +
                "\"xp_per_min\":805,\"level\":25,\"hero_damage\":53549,\"tower_damage\":8400,\"hero_healing\":0," +
                "\"gold\":4199,\"gold_spent\":19300,\"scaled_hero_damage\":30655,\"scaled_tower_damage\":5605," +
                "\"scaled_hero_healing\":0,\"ability_upgrades\":[{\"ability\":5179,\"time\":381,\"level\":1}," +
                "{\"ability\":5086,\"time\":499,\"level\":2},{\"ability\":5179,\"time\":531,\"level\":3}," +
                "{\"ability\":5086,\"time\":575,\"level\":4},{\"ability\":5179,\"time\":647,\"level\":5}," +
                "{\"ability\":5086,\"time\":676,\"level\":6},{\"ability\":5179,\"time\":777,\"level\":7}," +
                "{\"ability\":5086,\"time\":850,\"level\":8},{\"ability\":5447,\"time\":857,\"level\":9}," +
                "{\"ability\":6317,\"time\":907,\"level\":10},{\"ability\":5444,\"time\":1005,\"level\":11}," +
                "{\"ability\":5447,\"time\":1068,\"level\":12},{\"ability\":5444,\"time\":1113,\"level\":13}," +
                "{\"ability\":5444,\"time\":1268,\"level\":14},{\"ability\":5959,\"time\":1298,\"level\":15}," +
                "{\"ability\":5444,\"time\":1306,\"level\":16},{\"ability\":5447,\"time\":1531,\"level\":17}," +
                "{\"ability\":5942,\"time\":1801,\"level\":18},{\"ability\":6532,\"time\":2425,\"level\":19}]}," +
                "{\"account_id\":4294967295,\"player_slot\":1,\"hero_id\":12,\"item_0\":151,\"item_1\":22," +
                "\"item_2\":63,\"item_3\":81,\"item_4\":145,\"item_5\":252,\"backpack_0\":240,\"backpack_1\":0," +
                "\"backpack_2\":0,\"kills\":3,\"deaths\":9,\"assists\":9,\"leaver_status\":0,\"last_hits\":199," +
                "\"denies\":1,\"gold_per_min\":443,\"xp_per_min\":419,\"level\":20,\"hero_damage\":22735," +
                "\"tower_damage\":2660,\"hero_healing\":1084,\"gold\":1717,\"gold_spent\":15120," +
                "\"scaled_hero_damage\":10708,\"scaled_tower_damage\":2154,\"scaled_hero_healing\":360," +
                "\"ability_upgrades\":[{\"ability\":5083,\"time\":423,\"level\":1},{\"ability\":5088,\"time\":540," +
                "\"level\":2},{\"ability\":5083,\"time\":597,\"level\":3},{\"ability\":5237,\"time\":654," +
                "\"level\":4},{\"ability\":5088,\"time\":733,\"level\":5},{\"ability\":5088,\"time\":840," +
                "\"level\":6},{\"ability\":5083,\"time\":928,\"level\":7},{\"ability\":5088,\"time\":1003," +
                "\"level\":8},{\"ability\":5237,\"time\":1090,\"level\":9},{\"ability\":5906,\"time\":1193," +
                "\"level\":10},{\"ability\":5083,\"time\":1416,\"level\":11},{\"ability\":5237,\"time\":1573," +
                "\"level\":12},{\"ability\":5237,\"time\":1665,\"level\":13},{\"ability\":5300,\"time\":1797," +
                "\"level\":14},{\"ability\":5951,\"time\":2057,\"level\":15},{\"ability\":5300,\"time\":2120," +
                "\"level\":16},{\"ability\":5300,\"time\":2487,\"level\":17},{\"ability\":5972,\"time\":2843," +
                "\"level\":18}]},{\"account_id\":4294967295,\"player_slot\":2,\"hero_id\":104,\"item_0\":1," +
                "\"item_1\":127,\"item_2\":242,\"item_3\":114,\"item_4\":90,\"item_5\":214,\"backpack_0\":46," +
                "\"backpack_1\":0,\"backpack_2\":0,\"kills\":12,\"deaths\":4,\"assists\":17,\"leaver_status\":0," +
                "\"last_hits\":192,\"denies\":0,\"gold_per_min\":514,\"xp_per_min\":691,\"level\":25," +
                "\"hero_damage\":51720,\"tower_damage\":3278,\"hero_healing\":0,\"gold\":2281,\"gold_spent\":18550," +
                "\"scaled_hero_damage\":25137,\"scaled_tower_damage\":2468,\"scaled_hero_healing\":0," +
                "\"ability_upgrades\":[{\"ability\":5009,\"time\":377,\"level\":1},{\"ability\":5007,\"time\":500," +
                "\"level\":2},{\"ability\":5009,\"time\":539,\"level\":3},{\"ability\":5007,\"time\":647," +
                "\"level\":4},{\"ability\":5009,\"time\":719,\"level\":5},{\"ability\":5010,\"time\":798," +
                "\"level\":6},{\"ability\":5066,\"time\":867,\"level\":7},{\"ability\":5009,\"time\":951," +
                "\"level\":8},{\"ability\":5066,\"time\":1014,\"level\":9},{\"ability\":5007,\"time\":1162," +
                "\"level\":10},{\"ability\":6115,\"time\":1297,\"level\":11},{\"ability\":5010,\"time\":1350," +
                "\"level\":12},{\"ability\":5007,\"time\":1406,\"level\":13},{\"ability\":5066,\"time\":1509," +
                "\"level\":14},{\"ability\":5939,\"time\":1615,\"level\":15},{\"ability\":5066,\"time\":1686," +
                "\"level\":16},{\"ability\":5010,\"time\":1874,\"level\":17},{\"ability\":5970,\"time\":2146," +
                "\"level\":18},{\"ability\":6449,\"time\":2780,\"level\":19}]},{\"account_id\":4294967295," +
                "\"player_slot\":3,\"hero_id\":51,\"item_0\":119,\"item_1\":168,\"item_2\":63,\"item_3\":182," +
                "\"item_4\":108,\"item_5\":143,\"backpack_0\":11,\"backpack_1\":0,\"backpack_2\":0,\"kills\":7," +
                "\"deaths\":8,\"assists\":9,\"leaver_status\":0,\"last_hits\":218,\"denies\":2,\"gold_per_min\":504," +
                "\"xp_per_min\":675,\"level\":25,\"hero_damage\":30940,\"tower_damage\":954,\"hero_healing\":0," +
                "\"gold\":2445,\"gold_spent\":17940,\"scaled_hero_damage\":16226,\"scaled_tower_damage\":657," +
                "\"scaled_hero_healing\":0,\"ability_upgrades\":[{\"ability\":5597,\"time\":334,\"level\":1}," +
                "{\"ability\":5051,\"time\":502,\"level\":2},{\"ability\":5051,\"time\":630,\"level\":3}," +
                "{\"ability\":5597,\"time\":769,\"level\":4},{\"ability\":5051,\"time\":954,\"level\":5}," +
                "{\"ability\":5240,\"time\":1095,\"level\":6},{\"ability\":5597,\"time\":1220,\"level\":7}," +
                "{\"ability\":5051,\"time\":1260,\"level\":8},{\"ability\":5597,\"time\":1336,\"level\":9}," +
                "{\"ability\":6094,\"time\":1405,\"level\":10},{\"ability\":5596,\"time\":1539,\"level\":11}," +
                "{\"ability\":5240,\"time\":1587,\"level\":12},{\"ability\":5596,\"time\":1669,\"level\":13}," +
                "{\"ability\":5596,\"time\":1764,\"level\":14},{\"ability\":5941,\"time\":1964,\"level\":15}," +
                "{\"ability\":5596,\"time\":2059,\"level\":16},{\"ability\":5240,\"time\":2212,\"level\":17}," +
                "{\"ability\":6299,\"time\":2420,\"level\":18},{\"ability\":5976,\"time\":2848,\"level\":19}]}," +
                "{\"account_id\":4294967295,\"player_slot\":4,\"hero_id\":85,\"item_0\":231,\"item_1\":36," +
                "\"item_2\":216,\"item_3\":38,\"item_4\":46,\"item_5\":0,\"backpack_0\":0,\"backpack_1\":0," +
                "\"backpack_2\":0,\"kills\":1,\"deaths\":5,\"assists\":18,\"leaver_status\":0,\"last_hits\":65," +
                "\"denies\":1,\"gold_per_min\":311,\"xp_per_min\":499,\"level\":22,\"hero_damage\":8129," +
                "\"tower_damage\":5857,\"hero_healing\":4386,\"gold\":4886,\"gold_spent\":8065," +
                "\"scaled_hero_damage\":4211,\"scaled_tower_damage\":3524,\"scaled_hero_healing\":1778," +
                "\"ability_upgrades\":[{\"ability\":5299,\"time\":360,\"level\":1},{\"ability\":5050,\"time\":575," +
                "\"level\":2},{\"ability\":5299,\"time\":688,\"level\":3},{\"ability\":5180,\"time\":826," +
                "\"level\":4},{\"ability\":5299,\"time\":952,\"level\":5},{\"ability\":5049,\"time\":1198," +
                "\"level\":6},{\"ability\":5299,\"time\":1274,\"level\":7},{\"ability\":5180,\"time\":1385," +
                "\"level\":8},{\"ability\":5180,\"time\":1498,\"level\":9},{\"ability\":5180,\"time\":1620," +
                "\"level\":10},{\"ability\":5914,\"time\":1731,\"level\":11},{\"ability\":5049,\"time\":1951," +
                "\"level\":12},{\"ability\":5050,\"time\":2175,\"level\":13},{\"ability\":5050,\"time\":2224," +
                "\"level\":14},{\"ability\":6372,\"time\":2242,\"level\":15},{\"ability\":5050,\"time\":2332," +
                "\"level\":16},{\"ability\":5049,\"time\":2492,\"level\":17},{\"ability\":6141,\"time\":2803," +
                "\"level\":18}]},{\"account_id\":82741595,\"player_slot\":128,\"hero_id\":15,\"item_0\":50," +
                "\"item_1\":116,\"item_2\":257,\"item_3\":158,\"item_4\":0,\"item_5\":212,\"backpack_0\":0," +
                "\"backpack_1\":0,\"backpack_2\":44,\"kills\":3,\"deaths\":12,\"assists\":14,\"leaver_status\":0," +
                "\"last_hits\":191,\"denies\":9,\"gold_per_min\":380,\"xp_per_min\":431,\"level\":20," +
                "\"hero_damage\":24571,\"tower_damage\":1653,\"hero_healing\":2014,\"gold\":894,\"gold_spent\":12625," +
                "\"scaled_hero_damage\":12543,\"scaled_tower_damage\":1135,\"scaled_hero_healing\":713," +
                "\"ability_upgrades\":[{\"ability\":5239,\"time\":350,\"level\":1},{\"ability\":5678,\"time\":491," +
                "\"level\":2},{\"ability\":5087,\"time\":557,\"level\":3},{\"ability\":5239,\"time\":620," +
                "\"level\":4},{\"ability\":5239,\"time\":721,\"level\":5},{\"ability\":5067,\"time\":803," +
                "\"level\":6},{\"ability\":5678,\"time\":897,\"level\":7},{\"ability\":5678,\"time\":995," +
                "\"level\":8},{\"ability\":5678,\"time\":1152,\"level\":9},{\"ability\":5929,\"time\":1419," +
                "\"level\":10},{\"ability\":5067,\"time\":1459,\"level\":11},{\"ability\":5087,\"time\":1461," +
                "\"level\":12},{\"ability\":5087,\"time\":1668,\"level\":13},{\"ability\":5087,\"time\":1747," +
                "\"level\":14},{\"ability\":6120,\"time\":1910,\"level\":15},{\"ability\":5239,\"time\":2118," +
                "\"level\":16},{\"ability\":5067,\"time\":2544,\"level\":17},{\"ability\":5976,\"time\":2623," +
                "\"level\":18}]},{\"account_id\":67455577,\"player_slot\":129,\"hero_id\":113,\"item_0\":40," +
                "\"item_1\":0,\"item_2\":201,\"item_3\":178,\"item_4\":232,\"item_5\":29,\"backpack_0\":0," +
                "\"backpack_1\":0,\"backpack_2\":0,\"kills\":3,\"deaths\":13,\"assists\":13,\"leaver_status\":0," +
                "\"last_hits\":72,\"denies\":13,\"gold_per_min\":292,\"xp_per_min\":451,\"level\":21," +
                "\"hero_damage\":21718,\"tower_damage\":325,\"hero_healing\":0,\"gold\":931,\"gold_spent\":9635," +
                "\"scaled_hero_damage\":11406,\"scaled_tower_damage\":204,\"scaled_hero_healing\":0," +
                "\"ability_upgrades\":[{\"ability\":5468,\"time\":336,\"level\":1},{\"ability\":5679,\"time\":471," +
                "\"level\":2},{\"ability\":5298,\"time\":508,\"level\":3},{\"ability\":5679,\"time\":609," +
                "\"level\":4},{\"ability\":5679,\"time\":692,\"level\":5},{\"ability\":5683,\"time\":755," +
                "\"level\":6},{\"ability\":5679,\"time\":825,\"level\":7},{\"ability\":5468,\"time\":983," +
                "\"level\":8},{\"ability\":5298,\"time\":1145,\"level\":9},{\"ability\":6016,\"time\":1213," +
                "\"level\":10},{\"ability\":5298,\"time\":1257,\"level\":11},{\"ability\":5298,\"time\":1381," +
                "\"level\":12},{\"ability\":5468,\"time\":1668,\"level\":13},{\"ability\":5468,\"time\":1690," +
                "\"level\":14},{\"ability\":5959,\"time\":1722,\"level\":15},{\"ability\":5683,\"time\":1768," +
                "\"level\":16},{\"ability\":5683,\"time\":1984,\"level\":17},{\"ability\":6021,\"time\":2439," +
                "\"level\":18}],\"additional_units\":[{\"unitname\":\"\",\"item_0\":0,\"item_1\":0,\"item_2\":201," +
                "\"item_3\":178,\"item_4\":232,\"item_5\":29,\"backpack_0\":0,\"backpack_1\":0,\"backpack_2\":0}]}," +
                "{\"account_id\":55917782,\"player_slot\":130,\"hero_id\":89,\"item_0\":182,\"item_1\":147," +
                "\"item_2\":235,\"item_3\":137,\"item_4\":48,\"item_5\":11,\"backpack_0\":0,\"backpack_1\":0," +
                "\"backpack_2\":0,\"kills\":10,\"deaths\":6,\"assists\":10,\"leaver_status\":0,\"last_hits\":393," +
                "\"denies\":8,\"gold_per_min\":597,\"xp_per_min\":569,\"level\":23,\"hero_damage\":32721," +
                "\"tower_damage\":278,\"hero_healing\":0,\"gold\":233,\"gold_spent\":21090," +
                "\"scaled_hero_damage\":17764,\"scaled_tower_damage\":141,\"scaled_hero_healing\":0," +
                "\"ability_upgrades\":[{\"ability\":5469,\"time\":347,\"level\":1},{\"ability\":5467,\"time\":513," +
                "\"level\":2},{\"ability\":5469,\"time\":563,\"level\":3},{\"ability\":5467,\"time\":668," +
                "\"level\":4},{\"ability\":5469,\"time\":803,\"level\":5},{\"ability\":5085,\"time\":902," +
                "\"level\":6},{\"ability\":5467,\"time\":1004,\"level\":7},{\"ability\":5068,\"time\":1105," +
                "\"level\":8},{\"ability\":5469,\"time\":1190,\"level\":9},{\"ability\":6006,\"time\":1247," +
                "\"level\":10},{\"ability\":5467,\"time\":1309,\"level\":11},{\"ability\":5085,\"time\":1449," +
                "\"level\":12},{\"ability\":5068,\"time\":1524,\"level\":13},{\"ability\":5068,\"time\":1614," +
                "\"level\":14},{\"ability\":5907,\"time\":1701,\"level\":15},{\"ability\":5068,\"time\":1867," +
                "\"level\":16},{\"ability\":5085,\"time\":1886,\"level\":17},{\"ability\":5929,\"time\":2213," +
                "\"level\":18}]},{\"account_id\":4294967295,\"player_slot\":131,\"hero_id\":42,\"item_0\":127," +
                "\"item_1\":29,\"item_2\":34,\"item_3\":178,\"item_4\":108,\"item_5\":235,\"backpack_0\":0," +
                "\"backpack_1\":46,\"backpack_2\":0,\"kills\":15,\"deaths\":7,\"assists\":11,\"leaver_status\":0," +
                "\"last_hits\":109,\"denies\":4,\"gold_per_min\":418,\"xp_per_min\":640,\"level\":24," +
                "\"hero_damage\":36248,\"tower_damage\":19,\"hero_healing\":0,\"gold\":446,\"gold_spent\":14625," +
                "\"scaled_hero_damage\":23096,\"scaled_tower_damage\":19,\"scaled_hero_healing\":0," +
                "\"ability_upgrades\":[{\"ability\":5442,\"time\":343,\"level\":1},{\"ability\":5008,\"time\":511," +
                "\"level\":2},{\"ability\":5008,\"time\":625,\"level\":3},{\"ability\":5442,\"time\":735," +
                "\"level\":4},{\"ability\":5442,\"time\":854,\"level\":5},{\"ability\":5089,\"time\":916," +
                "\"level\":6},{\"ability\":5008,\"time\":980,\"level\":7},{\"ability\":5008,\"time\":1102," +
                "\"level\":8},{\"ability\":5442,\"time\":1211,\"level\":9},{\"ability\":5065,\"time\":1251," +
                "\"level\":10},{\"ability\":5065,\"time\":1421,\"level\":11},{\"ability\":5089,\"time\":1679," +
                "\"level\":12},{\"ability\":5065,\"time\":1684,\"level\":13},{\"ability\":5065,\"time\":1716," +
                "\"level\":14},{\"ability\":5938,\"time\":1875,\"level\":15},{\"ability\":6141,\"time\":1877," +
                "\"level\":16},{\"ability\":5089,\"time\":1984,\"level\":17},{\"ability\":6088,\"time\":2296," +
                "\"level\":18}]},{\"account_id\":4294967295,\"player_slot\":132,\"hero_id\":9,\"item_0\":0," +
                "\"item_1\":39,\"item_2\":237,\"item_3\":237,\"item_4\":0,\"item_5\":29,\"backpack_0\":0," +
                "\"backpack_1\":0,\"backpack_2\":0,\"kills\":0,\"deaths\":3,\"assists\":6,\"leaver_status\":2," +
                "\"last_hits\":12,\"denies\":0,\"gold_per_min\":150,\"xp_per_min\":145,\"level\":11," +
                "\"hero_damage\":5519,\"tower_damage\":0,\"hero_healing\":0,\"gold\":2,\"gold_spent\":1000," +
                "\"scaled_hero_damage\":2849,\"scaled_tower_damage\":0,\"scaled_hero_healing\":0," +
                "\"ability_upgrades\":[{\"ability\":5048,\"time\":338,\"level\":1},{\"ability\":5595,\"time\":937," +
                "\"level\":2},{\"ability\":5084,\"time\":982,\"level\":3},{\"ability\":5048,\"time\":1124," +
                "\"level\":4}]}],\"radiant_win\":true,\"duration\":2464,\"pre_game_duration\":90," +
                "\"start_time\":1505905358,\"match_id\":3454945048,\"match_seq_num\":3010000041," +
                "\"tower_status_radiant\":1974,\"tower_status_dire\":0,\"barracks_status_radiant\":63," +
                "\"barracks_status_dire\":0,\"cluster\":151,\"first_blood_time\":61,\"lobby_type\":0," +
                "\"human_players\":10,\"leagueid\":0,\"positive_votes\":0,\"negative_votes\":0,\"game_mode\":18," +
                "\"flags\":0,\"engine\":1,\"radiant_score\":41,\"dire_score\":32}");
        //
//        kafkaTemplate.metrics();
//
//        kafkaTemplate.execute(producer -> {
//            //这里可以编写kafka原生的api操作
//            return null;
//        });

        //消息发送的监听器，用于回调返回信息
        kafkaTemplate.setProducerListener(new ProducerListener<String, String>() {
            @Override
            public void onSuccess(String topic, Integer partition, String key, String value, RecordMetadata
                    recordMetadata) {

            }

            @Override
            public void onError(String topic, Integer partition, String key, String value, Exception exception) {

            }

            @Override
            public boolean isInterestedInSuccess() {
                return false;
            }
        });
    }

    /**
     * 简单版
     */
    public void sendMessage() {
        kafkaTemplate.send("player-topic", "aaa");
    }

}
