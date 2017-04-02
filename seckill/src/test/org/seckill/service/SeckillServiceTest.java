package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepectSeckillException;
import org.seckill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})

public class SeckillServiceTest {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void testGetById() throws Exception {
        long id=1008;
        Seckill seckill=seckillService.getById(id);
        logger.info("seckill={}",seckill);
    }

    @Test
    public void testGetSeckillList() throws Exception {
        List<Seckill> list=seckillService.getSeckillList();
        logger.info("list={}",list);
        //日志打印信息Closing non transactional SqlSession，不是在事务控制下，因为他是只读。
    }

    @Test
    //export暴露和excute执行在一起,完整性
    public void exportAndExecuteTogetherTest() throws Exception {
        long id=1015;
        long phone=15345676689L;
        Exposer exposer=seckillService.exportSeckillUrl(id);
        if (exposer.isExposed())
        {
            logger.info("exposer={}",exposer);
            String md5=exposer.getMd5();
            try {
                SeckillExecution seckillExecution=seckillService.executeSeckill(id,phone,md5);
                logger.info("seckillExecution={}",seckillExecution);
            } catch (RepectSeckillException e) {
                logger.error(e.getMessage());
            } catch (SeckillCloseException e) {
                logger.error(e.getMessage());
            }
        }
        else
        {
            //秒杀未开启
            logger.warn("exposer={}",exposer);
        }
        /**
         *  Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@6dcebe2a]
        exposer=Exposer{
         seckillId=1015,
         exposed=true,
         md5='b889b6e601f32bbff30f9d485861c8f8',
         now=null, start=null, end=null}
         12:38:41.251 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Creating a new SqlSession
          Registering transaction synchronization for SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@3bead2d]
         12:38:41.254 [main] DEBUG o.m.s.t.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@b987240] will be managed by Spring
         12:38:41.255 [main] DEBUG o.s.dao.SeckillDao.reduceNumber - ==>  Preparing: update seckill set number=number-1 where seckill_id=? and start_time <= ? and end_time >= ? and number>0;
         12:38:41.258 [main] DEBUG o.s.dao.SeckillDao.reduceNumber - ==> Parameters: 1015(Long), 2017-03-26 12:38:41.249(Timestamp), 2017-03-26 12:38:41.249(Timestamp)
         12:38:41.332 [main] DEBUG o.s.dao.SeckillDao.reduceNumber - <==    Updates: 1
         12:38:41.332 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@3bead2d]
         12:38:41.333 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Fetched SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@3bead2d] from current transaction
         12:38:41.335 [main] DEBUG o.s.d.S.insertSuccessKilled - ==>  Preparing: insert ignore into success_killed(seckill_id,user_phone,state) values(?,?,0)
         12:38:41.336 [main] DEBUG o.s.d.S.insertSuccessKilled - ==> Parameters: 1015(Long), 15345676689(Long)
         12:38:41.504 [main] DEBUG o.s.d.S.insertSuccessKilled - <==    Updates: 1
         12:38:41.504 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@3bead2d]
         12:38:41.506 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Fetched SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@3bead2d] from current transaction
         12:38:41.507 [main] DEBUG o.s.d.S.queryByIdWithSeckill - ==>  Preparing: select sk.seckill_id, sk.user_phone, sk.state, sk.create_time, s.seckill_id "seckill.seckill_id", s.name "seckill.name", s.number "seckill.number", s.start_time "seckill.start_time", s.end_time"seckill.end_time", s.create_time"seckill.create_time" from success_killed as sk inner join seckill as s on sk.seckill_id=s.seckill_id where sk.seckill_id=? and sk.user_phone=?
         12:38:41.511 [main] DEBUG o.s.d.S.queryByIdWithSeckill - ==> Parameters: 1015(Long), 15345676689(Long)
         12:38:41.543 [main] DEBUG o.s.d.S.queryByIdWithSeckill - <==      Total: 1
         12:38:41.543 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@3bead2d]
         12:38:41.545 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Transaction synchronization committing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@3bead2d]
         12:38:41.546 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Transaction synchronization deregistering SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@3bead2d]
         12:38:41.546 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Transaction synchronization closing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@3bead2d]
         12:38:41.581 [main] INFO  o.seckill.service.SeckillServiceTest - seckillExecution=SeckillExecution{seckillId=1015, state=1, stateInfo='秒杀成功', successKilled=SuccessKilled{seckillId=1015, userPhone=15345676689, state=0, createTime=Sun Mar 26 12:38:41 CST 2017}}

         */
    }

}