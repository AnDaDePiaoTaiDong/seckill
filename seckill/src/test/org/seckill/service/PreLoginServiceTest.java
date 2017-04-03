package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.PreLoginDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class PreLoginServiceTest {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PreLoginService preLoginService;

    @Test
    public void testIsExist() throws Exception {
        Long phone=15605695058L;
        boolean b=preLoginService.isExist(phone);
        logger.info("b={}",b);
    }

    @Test
    public void testRegisterUser() throws Exception {
        String username="xd";
        String password="root";
        Long phone=12222222222L;
        boolean b1=preLoginService.registerUser(username,password,phone);
        logger.info("b1={}",b1);

    }

    @Test
    public void testLogin() throws Exception {
        String username="xd";
        String password="root";
        Long phone=15605695058L;
        boolean b2=preLoginService.login(username,password,phone);
        logger.info("b2={}",b2);
    }
}