package org.larry.exercise.dao;

import org.apache.ibatis.annotations.Param;
import org.larry.exercise.entity.Seckill;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Larry on 2017/2/21.
 */
public interface SeckillDao {

    /**
    * Created on 2017/2/21 17:50
    * @param seckillId seckillId
    * @param killTime killTime
    */
    int reduceNumber(@Param("seckillId") Long seckillId,@Param("killTime") Date killTime) ;


    /**
    * Created on 2017/2/21 17:52
    * *query seckill
    */
    Seckill queryById(Long seckillId) ;

    /**
    * Created on 2017/2/21 17:52
    *query all
    */
    List<Seckill> queryAll(@Param("limit") int limit, @Param("offset") int offset);

    void killProcedure(Map<String,Object> mpas) ;
}
