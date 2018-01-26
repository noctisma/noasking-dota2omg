package com.noasking;

//import com.noasking.dota2.CoreApplication;
import com.noasking.dota2.repository.DicRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by MaJing on 2018/1/25.
 */
@RunWith(SpringRunner.class)
//@SpringBootTest(classes = CoreApplication.class)
public class CoreTest {

    @Autowired
    private DicRepository dicRepository;

    @Test
    public void testQueryDic(){
        System.out.println(dicRepository.findOne(1L));
    }

}
