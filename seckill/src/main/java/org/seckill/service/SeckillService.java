package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepectSeckillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

import java.util.List;

/**
 * 站在使用者角度设计接口
 */
public interface SeckillService {

    Seckill getById(long seckillId);

    List<Seckill> getSeckillList();

    Exposer exportSeckillUrl(long seckillId);

    SeckillExecution executeSeckill(long seckillId,long suerPhone,String md5)
            throws SeckillException,SeckillCloseException,RepectSeckillException;
}
