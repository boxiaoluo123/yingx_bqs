package com.baizhi.bqs;

import com.baizhi.bqs.dao.AdminDao;
import com.baizhi.bqs.dao.UserDao;
import com.baizhi.bqs.entity.Admin;
import com.baizhi.bqs.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YingxBqsApplicationTests {


    @Resource
    UserDao userDao;
    @Test
    public void test(){
        User user = userDao.queryByUsername("小黑");

        System.out.println("user = " + user);
    }




   /* @Test
    public void test(){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo("1");
        List<User> users = userMapper.selectByExample(userExample);

        users.forEach(user -> System.out.println(user));

    }
    @Test
    public void test5(){//查
        List<User> users = userMapper.queryAll();
        users.forEach(user -> System.out.println("user = " + user));

    }
    @Test
    public void test4(){//查询几条数据
        UserExample userExample = new UserExample();
        long l = userMapper.countByExample(userExample);
        System.out.println("l = " + l);

    }
    @Test
    public void test3(){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo("4");
        userMapper.deleteByExample(userExample);

    }
    @Test
    public void test2(){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo("2");
        User user = new User();
        user.setId("3");
        user.setUsername("西瓜222");
        //userMapper.updateByExample(user,userExample);//全修改
        userMapper.updateByExampleSelective(user,userExample);//局部修改
    }
    @Test
    public void test1(){
        UserExample userExample = new UserExample();

        User user1 = new User("3", "哈哈111", "Aa", "aa", "aa", "aa", "aa", new Date());
        userMapper.insert(user1);

    }
    @Test
    public void contextLoads() {
        Admin admin = adminDao.queryByUsername("admin");
        System.out.println("admin = " + admin);


    }
*/
}
