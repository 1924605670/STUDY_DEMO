package com.study.redis;

import com.study.redis.pojo.User;
import com.study.redis.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chengzhihua
 * @description
 * @date 2020/10/14
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTest {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test(){
//        redisTemplate.opsForValue().set("abc","1");
//        redisUtil.set("nihao",new User("张三",5));
        User user = (User)redisUtil.get("nihao");
        System.out.println(user.getName()+"="+user.getAge());

    }

}
