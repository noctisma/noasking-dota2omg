package com.noasking.dota2.web.service;

import com.google.common.collect.Maps;
import com.noasking.dota2.entity.AbilityEntity;
import com.noasking.dota2.entity.HeroEntity;
import com.noasking.dota2.repository.HeroRepository;
import com.noasking.dota2.web.WebConst;
import com.noasking.dota2.web.utils.URLImageDownload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by MaJing on 2018/2/6.
 */
@Service
public class HeroParseService {

    @Autowired
    private HeroRepository heroRepository;

    /**
     * 下载技能图片
     *
     * @param abilityIds 可选，如果为空，则下载全部
     */
    public int downloadAbilityImages(List<Integer> abilityIds) {
        Map<Integer, String> maps = Maps.newHashMap();
        if (abilityIds == null || abilityIds.size() == 0) {
            List<HeroEntity> heroEntityList = heroRepository.findAll();
            for (HeroEntity heroEntity : heroEntityList) {
                maps.put(heroEntity.getId(), heroEntity.getName());
            }
        } else {
            for (Integer id : abilityIds) {
                HeroEntity heroEntity = heroRepository.findOne(id);
                if (heroEntity != null) {
                    maps.put(heroEntity.getId(), heroEntity.getName());
                }
            }
        }
        return downloadImages(maps);
    }

    private int downloadImages(Map<Integer, String> maps) {
        int result = 0;
        for (Map.Entry<Integer, String> entry : maps.entrySet()) {
            boolean rst = URLImageDownload.download(WebConst.Dota2CdnUrl.HERO + entry.getValue() + "_vert.jpg",
                    WebConst.ImagePath
                            .HERO + entry.getKey() + ".jpg");
            if (rst) {
                result++;
            }else {
//                heroRepository.delete(entry.getKey());
            }
        }
        return result;
    }

}
