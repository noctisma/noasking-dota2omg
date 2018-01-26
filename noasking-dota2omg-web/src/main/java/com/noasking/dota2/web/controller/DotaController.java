package com.noasking.dota2.web.controller;

import com.noasking.dota2.entity.RankingListEntity;
import com.noasking.dota2.repository.RankingListRepository;
import com.noasking.dota2.web.service.HiveSQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by MaJing on 2018/1/25.
 */
@RestController
public class DotaController {

    @Autowired
    private RankingListRepository rankingListRepository;

    /**
     * @return
     */
    @RequestMapping("getHeroTopList")
    public ResponseEntity<?> getHeroTopList(String timeType) {
        List<RankingListEntity> rankingListEntityList = rankingListRepository.findByCodeOrderByPercentDesc("HERO_" + HiveSQLService
                .CountTimeType.valueOf(timeType));
        return new ResponseEntity<Object>(rankingListEntityList, HttpStatus.OK);
    }

    @RequestMapping("getAbilityTopList")
    public ResponseEntity<?> getAbilityTopList(String timeType) {
        List<RankingListEntity> rankingListEntityList = rankingListRepository.findByCodeOrderByPercentDesc("ABILITY_" + HiveSQLService
                .CountTimeType.valueOf(timeType));
        return new ResponseEntity<Object>(rankingListEntityList, HttpStatus.OK);
    }

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * 上传拍摄图片情况,然后通过opencv解析，分析，返回分析结果
     */
    @RequestMapping(value = "/uploadAbilityPickerImage", method = RequestMethod.POST)
    public ResponseEntity<?> uploadAbilityPickerImage(@RequestParam("file") MultipartFile file,
                                                      HttpServletRequest request, String jobId) throws
            InterruptedException {
        for (int i = 0; i < 5; i++) {
            messagingTemplate.convertAndSend("/topic/progress/" + jobId, new Progress(i * 20, "处理到：" + i));
            Thread.currentThread().sleep(2000L);
        }
        return new ResponseEntity<Object>(new String("处理成功"), HttpStatus.OK);


    }

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public void sendMessage(String jobId) throws InterruptedException {

        for (int i = 0; i < 5; i++) {
            System.out.println("aaaaaa");
            messagingTemplate.convertAndSend("/user/topic/message", new Progress(i * 20, "处理到：" + i));
            Thread.currentThread().sleep(2000L);
        }
    }

    class Progress {

        private int percent;
        private String message;

        public Progress(int percent, String message) {
            this.percent = percent;
            this.message = message;
        }

        public int getPercent() {
            return percent;
        }

        public String getMessage() {
            return message;
        }
    }


}
