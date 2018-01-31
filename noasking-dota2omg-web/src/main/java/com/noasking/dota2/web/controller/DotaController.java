package com.noasking.dota2.web.controller;

import com.noasking.dota2.entity.AbilityEntity;
import com.noasking.dota2.entity.HeroEntity;
import com.noasking.dota2.entity.RankingListEntity;
import com.noasking.dota2.repository.AbilityRepository;
import com.noasking.dota2.repository.HeroRepository;
import com.noasking.dota2.repository.RankingListRepository;
import com.noasking.dota2.web.service.CacheService;
import com.noasking.dota2.web.service.HiveSQLService;
import com.noasking.dota2.web.vo.RankingListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MaJing on 2018/1/25.
 */
@RestController
@RequestMapping("api")
public class DotaController {

    @Autowired
    private RankingListRepository rankingListRepository;

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private AbilityRepository abilityRepository;

    @Autowired
    private CacheService cacheService;

    /**
     * 获取英雄胜率排行榜信息
     *
     * @param timeType
     * @return
     */
    @RequestMapping("getHeroTopList")
    public ResponseEntity<?> getHeroTopList(String timeType) {
        List<RankingListEntity> rankingListEntityList = rankingListRepository.findByCodeOrderByPercentDesc("HERO_" +
                HiveSQLService
                        .CountTimeType.valueOf(timeType));
        List<RankingListVO> rankingListVOList = new ArrayList<>(rankingListEntityList.size());
        for (RankingListEntity rankingListEntity : rankingListEntityList) {
            HeroEntity heroEntity = cacheService.getHeroById(Integer.parseInt(rankingListEntity.getKeyword()));
            if (heroEntity == null) {
                continue;
            }
            RankingListVO rankingListVO = new RankingListVO();
            rankingListVO.setName(heroEntity.getName());
            rankingListVO.setLocalizedName(heroEntity.getLocalizedName());
            rankingListVO.setPercent(rankingListEntity.getPercent());
            rankingListVO.setTotal(rankingListEntity.getTotal());
            rankingListVOList.add(rankingListVO);
        }
        return new ResponseEntity<Object>(rankingListVOList, HttpStatus.OK);
    }

    /**
     * 获取技能排行榜信息
     *
     * @param timeType
     * @return
     */
    @RequestMapping("getAbilityTopList")
    public ResponseEntity<?> getAbilityTopList(String timeType) {
        List<RankingListEntity> rankingListEntityList = rankingListRepository.findByCodeOrderByPercentDesc("ABILITY_"
                + HiveSQLService
                .CountTimeType.valueOf(timeType));
        List<RankingListVO> rankingListVOList = new ArrayList<>(rankingListEntityList.size());
        for (RankingListEntity rankingListEntity : rankingListEntityList) {
            AbilityEntity abilityEntity = cacheService.getAbilityById(Integer.parseInt(rankingListEntity.getKeyword()));
            if (abilityEntity == null) {
                continue;
            }
            RankingListVO rankingListVO = new RankingListVO();
            rankingListVO.setName(abilityEntity.getName());
            rankingListVO.setLocalizedName(abilityEntity.getLocalizedName());
            rankingListVO.setPercent(rankingListEntity.getPercent());
            rankingListVO.setTotal(rankingListEntity.getTotal());
            rankingListVOList.add(rankingListVO);
        }
        return new ResponseEntity<Object>(rankingListVOList, HttpStatus.OK);
    }

    /**
     * 获取所有英雄信息
     *
     * @return
     */
    @RequestMapping("getAllHero")
    public ResponseEntity<?> getAllHero() {
        return new ResponseEntity<Object>(heroRepository.findAll(), HttpStatus.OK);
    }

    /**
     * 获取所有技能信息
     *
     * @return
     */
    @RequestMapping("getAllAbility")
    public ResponseEntity getAllAbility() {
        return new ResponseEntity<Object>(abilityRepository.findAll(), HttpStatus.OK);
    }

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("/uploadClient")
    public String testUpload(@RequestParam("file") MultipartFile file) throws IOException {
//        System.out.println("后台文件上传函数");
//        System.out.println("获取到的文件名称为：" + file);
        String filePath = file.getOriginalFilename(); // 获取文件的名称
        System.out.println(filePath);
//        filePath = "asset/" + filePath; // 这是文件的保存路径，如果不设置就会保存到项目的根目录
//        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
//        outputStream.write(file.getBytes());
//        outputStream.flush();
//        outputStream.close();
        return "客户资料上传成功";
    }

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
