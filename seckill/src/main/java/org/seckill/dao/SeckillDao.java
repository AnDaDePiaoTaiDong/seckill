package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**dao接口对应数据的增删改查
 * Created by xudong on 2017/3/23.
 */
public interface SeckillDao {
    //减库存,@Param("seckillId")是用来告诉mybatis区别两个参数
    int reduceNumber(@Param("seckillId")long seckillId,@Param("killTime")Date killTime);
    //根据id查询
    Seckill queryById(long seckillId);
    //根据偏移量查询秒杀商品列表
    List<Seckill> queryAll(@Param("offset")int offset,@Param("limit")int limit);

}
