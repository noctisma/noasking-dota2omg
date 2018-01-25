package com.noasking.dota2.clawer.config;

import com.noasking.dota2.clawer.api.ApiConst;
import com.noasking.dota2.clawer.api.SteamDota2Api;
import com.noasking.dota2.entity.DicEntity;
import com.noasking.dota2.repository.DicRepository;
import com.noasking.dota2.clawer.service.DicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MaJing on 2017/11/9.
 */
@Component
@Configurable
@Lazy(false)
public class StartJob {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SteamDota2Api steamDota2Api;

    @Autowired
    private DicRepository dicRepository;

    @Autowired
    private DicService dicService;

    @Autowired
    private JobProperties jobProperties;

    private static Map<String, DicEntity> dicEntities = new HashMap<>();

    /**
     * 实时下载任务
     */
    @Scheduled(fixedDelay = 30 * 1000)
    public void realTimeJob() throws IOException {
        DicEntity dicEntity = dicRepository.findOne(1L); // ID为0记录实时访问记录
        long max = Long.MAX_VALUE;
        while (true) {
            try {
                logger.info("当前实时任务起始序列号：" + dicEntity.getValue());
                Long seq = steamDota2Api.getMatchHistoryBySequenceNum(Long.parseLong(dicEntity.getValue()) + 1,
                        max);
                if (seq != 0) {
                    dicEntity.setValue(String.valueOf(seq));
                    dicRepository.save(dicEntity);
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
                try {
                    Thread.currentThread().sleep(3000L);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

        }


    }

    @Scheduled(fixedDelay = 30 * 1000)
    public void fixedDelayJob() throws IOException {
        logger.info("-----------------------"+String.valueOf(jobProperties.getSize()));
        for (int i = 0; i < jobProperties.getSize(); i++) {
            Thread thread = new HistoryJob(null);
            thread.start();
        }
    }

    class HistoryJob extends Thread {
        private String name;

        public HistoryJob(String name) {
            this.name = name;
        }

        public void run() {
            DicEntity dicEntity = dicService.getUnlockDic();
            if (dicEntity == null)
                return;
            this.name = dicEntity.getKey2();
            dicEntities.put(name, dicEntity);
            Thread.currentThread().setName("thread:" + name);
            logger.info(name + "MatchSeq任务开始调度");
            long max = Long.parseLong(dicEntity.getRemarks());
            while (true) {
                try {
                    if (Long.parseLong(dicEntity.getValue()) >= max) {
                        break;
                    }
                    Long seq = steamDota2Api.getMatchHistoryBySequenceNum(Long.parseLong(dicEntity.getValue()) + 1,
                            max);
                    if (seq != 0) {
                        dicEntity.setValue(String.valueOf(seq));
                        dicRepository.save(dicEntity);
                    }
                    if (seq >= max) {
                        break;
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    try {
                        Thread.currentThread().sleep(3000L);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }

            }
            dicEntity.setStatus(ApiConst.DicState.OVER);
            dicRepository.save(dicEntity);
            dicEntities.remove(name);
            Thread thread = new HistoryJob(null);
            thread.start();
        }
    }

    @PreDestroy
    public void destory() {
        for (DicEntity dicEntity : dicEntities.values()) {
            dicEntity.setStatus(ApiConst.DicState.CREATE);
            dicRepository.save(dicEntity);
        }
    }

}
