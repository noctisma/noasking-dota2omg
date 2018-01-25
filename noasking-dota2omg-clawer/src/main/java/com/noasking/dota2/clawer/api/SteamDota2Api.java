package com.noasking.dota2.clawer.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.noasking.dota2.clawer.config.JobProperties;
import com.noasking.dota2.entity.GameItemEntity;
import com.noasking.dota2.entity.HeroEntity;
import com.noasking.dota2.clawer.model.match.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

/**
 * Created by MaJing on 2017/11/9.
 */
@Component
public class SteamDota2Api {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JobProperties jobProperties;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public String getUrlStr(String url, Map<String, Object> param) {
        StringBuffer sb = new StringBuffer();
        sb.append(ApiConst.BASE_URL).append(url).append("?key=").append(jobProperties.getKey());
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            if (!StringUtils.isEmpty(entry.getValue())) {
                sb.append('&').append(entry.getKey()).append('=').append(entry.getValue());
            }
        }
        return sb.toString();
    }


    public long getMatchHistoryBySequenceNum(long start_at_match_seq_num, long max) throws IOException {
        logger.info("start_at_match_seq_num：" + start_at_match_seq_num);
        Map<String, Object> param = new HashMap<>();
        param.put("start_at_match_seq_num", start_at_match_seq_num);
        //param.put("matches_requested", 100);
        String response = restTemplate.getForObject(getUrlStr(ApiConst.GET_MATCH_HISTORY_BY_SEQ_NUM, param), String
                .class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode result = objectMapper.readTree(response).get("result");
        String status = result.get("status").asText();
        logger.info("status:" + status);
        if ("1".endsWith(status)) {
            Iterator<JsonNode> jsonNodeIterable = result.get("matches").elements();
            while (jsonNodeIterable.hasNext()) {
                JsonNode jsonNode = jsonNodeIterable.next();
                String gameMode = jsonNode.get("game_mode").asText();
                if (start_at_match_seq_num > max) {
                    return max;
                }
                start_at_match_seq_num = jsonNode.get("match_seq_num").asLong();
                if ("18".equals(gameMode)) {
                    kafkaTemplate.send("d-topic", jsonNode.toString());
                }
                //Match match = objectMapper.readValue(jsonNode.toString(), Match.class);
            }
            return start_at_match_seq_num;
        } else {
            throw new RuntimeException();
        }
    }


    /**
     * @param game_mode         游戏模式
     * @param start_at_match_id 起始游戏ID
     * @return
     * @throws IOException
     */
    public void getMatchHistory(int game_mode, long start_at_match_id) throws IOException {
        logger.info("--" + start_at_match_id);
        Map<String, Object> param = new HashMap<>();
        param.put("game_mode", game_mode);
        param.put("start_at_match_id", start_at_match_id);
        String response = restTemplate.getForObject(getUrlStr(ApiConst.GET_MATCH_HISTORY, param), String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode result = objectMapper.readTree(response).get("result");
        String status = result.get("status").asText();
        logger.info("status:" + status);
        if ("1".endsWith(status)) {
            Iterator<JsonNode> jsonNodeIterable = result.get("matches").elements();
            List<Match> saleOrderItems = new ArrayList<Match>();

        }
    }

    /**
     * 获取游戏物品信息
     *
     * @param language 语言（可选） 中文是zh_CN
     * @return
     */
    public List<GameItemEntity> getGameItems(String language) throws IOException {
        logger.info("调用获取游戏物品接口，使用语言:" + language);
        Map<String, Object> param = new HashMap<>();
        param.put("language", language);
        String response = restTemplate.getForObject(getUrlStr(ApiConst.GET_GAME_ITEMS, param), String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        JsonNode result = objectMapper.readTree(response).get("result");
        Iterator<JsonNode> jsonNodeIterable = result.get("items").elements();
        List<GameItemEntity> gameItemList = new ArrayList<GameItemEntity>();
        while (jsonNodeIterable.hasNext()) {
            JsonNode jsonNode = jsonNodeIterable.next();
            GameItemEntity gameItem = objectMapper.readValue(jsonNode.toString(), GameItemEntity.class);
            gameItemList.add(gameItem);
        }
        return gameItemList;
    }

    /**
     * 获取游戏物品信息
     *
     * @param language 语言（可选） 中文是zh_CN
     * @return
     */
    public List<HeroEntity> getHeroes(String language) throws IOException {
        logger.info("调用获取英雄接口，使用语言:" + language);
        Map<String, Object> param = new HashMap<>();
        param.put("language", language);
        String response = restTemplate.getForObject(getUrlStr(ApiConst.GET_HEROES, param), String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        JsonNode result = objectMapper.readTree(response).get("result");
        Iterator<JsonNode> jsonNodeIterable = result.get("heroes").elements();
        List<HeroEntity> heroEntities = new ArrayList<HeroEntity>();
        while (jsonNodeIterable.hasNext()) {
            JsonNode jsonNode = jsonNodeIterable.next();
            HeroEntity gameItem = objectMapper.readValue(jsonNode.toString(), HeroEntity.class);
            heroEntities.add(gameItem);
        }
        return heroEntities;
    }


}
