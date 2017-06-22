package com;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dao.UserRepository;
import com.entity.User;


//@RunWith就是一个运行器 下面2个都可以，如果不使用RunWith注解，那么JUnit将会采用默认的执行类Suite执行
//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
//通过springboot的启动类来加载配置文件信息
//@SpringApplicationConfiguration(classes = Application.class)过时
@SpringBootTest(classes = Demo1Application.class)
//直接加载 现在不适用spring配置文件
//@ContextConfiguration(locations={"classpath:spring/spring-*.xml"})
@WebAppConfiguration
//@ActiveProfiles("dev")
public class SpringBootJdbcDemoApplicationTests
{
     Logger logger= LoggerFactory.getLogger(SpringBootJdbcDemoApplicationTests.class);
    @Autowired
    private UserRepository userRepository;

    @Test 
    public void testAll(){
        findAllUsers();
        findUserById();
        createUser();
    }

    @Test
    public void findAllUsers()  {
        List<User> users = userRepository.findAll();
        assertNotNull(users);
        assertTrue(!users.isEmpty());

    }

    @Test
    public void findUserById()  {
    	//启动类 如果没有放对位置，这里注入bean是失败的，userRepository创建失败！！！！
        User user = userRepository.findUserById(1);
        System.out.println(user);
        assertNotNull(user);
    }
    private void updateById(Integer id)  {
        User newUser = new User(id, "JackChen", "JackChen@qq.com",123454554);
        userRepository.update(newUser);
        User newUser2 = userRepository.findUserById(newUser.getId());
        assertEquals(newUser.getName(), newUser2.getName());
        assertEquals(newUser.getEmail(), newUser2.getEmail());
    }
    @Test
    public void createUser() {
        User user = new User(0, "tom", "tom@gmail.com",987556788);
        User savedUser = userRepository.create(user);
        logger.debug("{}",savedUser);
        User newUser = userRepository.findUserById(savedUser.getId());
        assertEquals("tom", newUser.getName());
        assertEquals("tom@gmail.com", newUser.getEmail());
        updateById(newUser.getId());
        userRepository.delete(newUser.getId());
    }
}
