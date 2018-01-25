import com.noasking.dota2.ClawerDota2Application;
import com.noasking.dota2.clawer.api.SteamDota2Api;
import com.noasking.dota2.entity.DicEntity;
import com.noasking.dota2.entity.GameItemEntity;
import com.noasking.dota2.entity.HeroEntity;
import com.noasking.dota2.repository.DicRepository;
import com.noasking.dota2.repository.GameItemRepository;
import com.noasking.dota2.repository.HeroRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

/**
 * Created by MaJing on 2017/11/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ClawerDota2Application.class)
public class ApiTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private SteamDota2Api steamDota2Api;

    @Autowired
    private DicRepository dicRepository;

    @Autowired
    private GameItemRepository gameItemRepository;

    @Autowired
    private HeroRepository heroRepository;

    /**
     * 导入游戏物品
     *
     * @throws IOException
     */
    @Test
    public void testGetGameItem() throws IOException {
        List<GameItemEntity> gameItemList = steamDota2Api.getGameItems("zh_CN");

        System.out.println(gameItemList.size());
        gameItemRepository.save(gameItemList);

    }

    /**
     * 导入游戏英雄
     *
     * @throws IOException
     */
    @Test
    public void testGetHeroes() throws IOException {
        List<HeroEntity> heroEntities = steamDota2Api.getHeroes("zh_CN");

        System.out.println(heroEntities.size());
        heroRepository.save(heroEntities);

    }

    @Test
    public void testMatchHisBySeq() throws IOException {
        for (long i = 3000; i < 3108; i++) {
            DicEntity dicEntity = new DicEntity();
            dicEntity.setKey2("job" + i);
            dicEntity.setValue(String.valueOf(i * 1000000));
            dicEntity.setRemarks(String.valueOf((i + 1) * 1000000));
            dicRepository.save(dicEntity);
        }
        //System.out.println(steamDota2Api.getMatchHistoryBySequenceNum(3087988381L,990000).size());
    }

    /**
     * 73B529299291F7BB4EC9975052C00D69
     * 2B58A9C96FD73EC323CCFDA5383C4BBC
     * EFA1E81676FCC47157EA871A67741EF5
     * 42D51B3ACCEC6EB600D85D5D2917F75B
     *
     * @throws IOException
     */

    @Test
    public void testMatchHis2() throws IOException, InterruptedException {
        for (int i = 3000; i < 3010; i++) {
//            steamDota2Api.setKey("73B529299291F7BB4EC9975052C00D69");
            Thread thread = new MyThread2("job" + i);
            Thread.currentThread().sleep(500L);
            thread.start();
        }
        try {
            Thread.currentThread().sleep(10000000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMatchHis3() throws IOException {
        for (int i = 3010; i < 3020; i++) {
//            steamDota2Api.setKey("EFA1E81676FCC47157EA871A67741EF5");
            Thread thread = new MyThread2("job" + i);
            thread.start();
        }
        try {
            Thread.currentThread().sleep(10000000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMatchHis4() throws IOException {
        for (int i = 3020; i < 3030; i++) {
//            steamDota2Api.setKey("2B58A9C96FD73EC323CCFDA5383C4BBC");
            Thread thread = new MyThread2("job" + i);
            thread.start();
        }
        try {
            Thread.currentThread().sleep(10000000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMatchHis5() throws IOException {
        for (int i = 3030; i < 3040; i++) {
//            steamDota2Api.setKey("42D51B3ACCEC6EB600D85D5D2917F75B");
            Thread thread = new MyThread2("job" + i);
            thread.start();
        }
        try {
            Thread.currentThread().sleep(10000000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    class MyThread2 extends Thread {
        private String name;

        public MyThread2(String name) {
            this.name = name;
        }

        public void run() {
            Thread.currentThread().setName("thread:" + name);
            logger.info(name + "MatchSeq任务开始调度");
            DicEntity dicEntity = dicRepository.findByKey2(name);
            long max = Long.parseLong(dicEntity.getRemarks());
            while (true) {
                try {
                    Long seq = steamDota2Api.getMatchHistoryBySequenceNum(Long.parseLong(dicEntity.getValue()) + 1, max);
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
                        Thread.currentThread().sleep(30000L);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        }
    }
}

