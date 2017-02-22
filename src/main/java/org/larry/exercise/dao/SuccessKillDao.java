package org.larry.exercise.dao;

import org.apache.ibatis.annotations.Param;
import org.larry.exercise.entity.SuccessKill;

/**
 * Created by Larry on 2017/2/21.
 */
public interface SuccessKillDao {

    int insert(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone) ;

    SuccessKill queryByIdWithSeckill(@Param("seckillId") Long seckillId,@Param("userPhone")Long userPhone) ;
}
