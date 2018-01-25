package com.noasking.dota2.clawer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noasking.dota2.clawer.entity.AbilityUpgradeEntity;
import com.noasking.dota2.clawer.entity.MatchDetailEntity;
import com.noasking.dota2.clawer.entity.PlayerEntity;
import com.noasking.dota2.clawer.model.match.Match;
import com.noasking.dota2.clawer.model.player.AbilityUpgrade;
import com.noasking.dota2.clawer.model.player.Player;
import com.noasking.dota2.clawer.repository.AbilityUpgradeRepository;
import com.noasking.dota2.clawer.repository.MatchDetailRepository;
import com.noasking.dota2.clawer.repository.PlayerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MaJing on 2017/12/1.
 */
@Service
public class MatchService {

    @Autowired
    private AbilityUpgradeRepository abilityUpgradeRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private MatchDetailRepository matchDetailRepository;

    /**
     * 比赛数据转化
     * @return true：成功；false：失败
     */
    @Transactional
    public boolean transMatch(String data){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Match match = objectMapper.readValue(data,Match.class);
            MatchDetailEntity matchDetailEntity = new MatchDetailEntity();
            BeanUtils.copyProperties(match,matchDetailEntity);
            List<Player> playerList = match.getPlayers();
            if(playerList != null && playerList.size() != 0){
                List<PlayerEntity> playerEntityList = new ArrayList<>(playerList.size());
                for (Player player:playerList) {
                    PlayerEntity playerEntity = new PlayerEntity();
                    BeanUtils.copyProperties(player,playerEntity);
                    playerEntity.setMatch_id(match.getMatch_id());
                    playerEntityList.add(playerEntity);
                    List<AbilityUpgrade> abilityUpgradeList = player.getAbility_upgrades();
                    if(abilityUpgradeList!= null && abilityUpgradeList.size() != 0){
                        List<AbilityUpgradeEntity> abilityUpgradeEntityList = new ArrayList<>(abilityUpgradeList.size());
                        for (AbilityUpgrade abilityUpgrade:abilityUpgradeList) {
                            AbilityUpgradeEntity abilityUpgradeEntity = new AbilityUpgradeEntity();
                            BeanUtils.copyProperties(abilityUpgrade,abilityUpgradeEntity);
                            abilityUpgradeEntity.setAccount_id(player.getAccount_id());
                            abilityUpgradeEntity.setMatch_id(match.getMatch_id());
                            abilityUpgradeEntityList.add(abilityUpgradeEntity);
                        }
                        abilityUpgradeRepository.save(abilityUpgradeEntityList);
                    }
                }
                playerRepository.save(playerEntityList);
            }
            matchDetailRepository.save(matchDetailEntity);
            return true;
        } catch (IOException e) {
            return false;
        }
    }



}
