import com.noasking.dota2.ClawerDota2Application;
import com.noasking.dota2.repository.DicRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by MaJing on 2017/12/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ClawerDota2Application.class)
public class DataInputTest {

    @Autowired
    private DicRepository dicRepository;

    @Test
    public void testQueryDic(){
        System.out.println(dicRepository.findOne(1L));
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

}
