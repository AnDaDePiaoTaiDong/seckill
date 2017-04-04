package org.seckill.service.impl;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepectSeckillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * Created by xudong on 2017/3/25.
 */
@Service//注意spring注入service的是实现类，所以后面可以直接调用接口的方法
public class SeckillServiceImpl implements SeckillService{

    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;

    private final String slat="hkdaahabdhbvoa";

    @Override
    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0,6);
    }

    @Override
    //Exposer,SeckillExecution执行一起
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill=seckillDao.queryById(seckillId);
        if (seckill==null)
            return new Exposer(false,seckillId);
        Date startTime=seckill.getStartTime();
        Date endTime=seckill.getEndTime();
        Date nowTime=new Date();
        if (startTime.getTime()>nowTime.getTime()||endTime.getTime()<nowTime.getTime())
            return new Exposer(false,seckillId,nowTime,startTime,endTime);
        String md5=getMD5(seckillId);
        return new Exposer(true,md5,seckillId);
    }
    private String getMD5(long seckillId)
    {
        String base=seckillId+"/"+slat;
        String md5= DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Override
    @Transactional//标注这是事务操作
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, SeckillCloseException, RepectSeckillException
    {
        if (md5==null||!md5.equals(getMD5(seckillId)))
            throw new SeckillException("seckill data rewrite");
        //执行秒杀逻辑，减库存+记录购买行为
        Date nowTime=new Date();
        try {
            int updateCount=seckillDao.reduceNumber(seckillId,nowTime);
            if (updateCount<=0)
            {
                throw  new SeckillCloseException("seckill is close");
            }
            else
            {
                int insertCount= successKilledDao.insertSuccessKilled(seckillId,userPhone);
                if (insertCount<=0)
                {
                    throw new RepectSeckillException("seckill repeated");
                }
                else
                {
                    SuccessKilled successKilled=successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS,successKilled);
                }
            }
        } catch (SeckillCloseException e1) {
            throw e1;
        } catch (RepectSeckillException e2) {
            throw e2;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new SeckillException("seckill in error"+e.getMessage());
        }
    }
}
