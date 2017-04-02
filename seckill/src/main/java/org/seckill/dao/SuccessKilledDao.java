package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**dao接口对应数据的增删改查
 * Created by xudong on 2017/3/23.
 */

public interface SuccessKilledDao {
    //插入购买明细
    int insertSuccessKilled(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
    //根据id查询购买明细，并携带seckill实体
    SuccessKilled queryByIdWithSeckill(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
}
