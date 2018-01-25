import com.noasking.dota2.clawer.ClawerDota2TestApplication;
import com.noasking.dota2.clawer.entity.MatchEntity;
import com.noasking.dota2.clawer.repository.MatchRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by MaJing on 2017/12/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ClawerDota2TestApplication.class)
public class DataInputTest {


    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     *
     */
    @Test
    public void testKafka(){
        while (true){
            List<MatchEntity> matchEntityList = matchRepository.getUseMatchs();
            if(matchEntityList == null || matchEntityList.size() == 0)
                break;
            for (MatchEntity match:matchEntityList) {
                kafkaTemplate.send("d-topic",match.getDetail());
                match.setStatus("O");
                matchRepository.save(match);
            }
            System.out.println("----------------------------");
        }


    }

}
