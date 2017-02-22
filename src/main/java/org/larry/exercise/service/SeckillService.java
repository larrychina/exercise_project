package org.larry.exercise.service;

import org.larry.exercise.dto.Exposer;
import org.larry.exercise.dto.SeckillExecution;
import org.larry.exercise.entity.Seckill;
import org.larry.exercise.exeception.ReapeatkillExeception;
import org.larry.exercise.exeception.SeckillCloseExeception;
import org.larry.exercise.exeception.SeckillExeception;

import java.util.List;

/**
 * Created by Larry on 2017/2/22.
 */
public interface SeckillService {

    Seckill getById(Long seckillId) ;

    List<Seckill> getAll() ;

    // 暴露秒杀接口
    Exposer exportSeckillUrl(Long seckillId) ;

    // 执行秒少操作
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws ReapeatkillExeception,SeckillExeception,SeckillCloseExeception;
}
