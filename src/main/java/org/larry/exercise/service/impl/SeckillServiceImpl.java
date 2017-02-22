package org.larry.exercise.service.impl;

import org.larry.exercise.dao.SeckillDao;
import org.larry.exercise.dao.SuccessKillDao;
import org.larry.exercise.dto.Exposer;
import org.larry.exercise.dto.SeckillExecution;
import org.larry.exercise.entity.Seckill;
import org.larry.exercise.entity.SuccessKill;
import org.larry.exercise.enums.SeckillEnums;
import org.larry.exercise.exeception.ReapeatkillExeception;
import org.larry.exercise.exeception.SeckillCloseExeception;
import org.larry.exercise.exeception.SeckillExeception;
import org.larry.exercise.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Larry on 2017/2/22.
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass()) ;

    // 用于混淆md5
    private final String slat = "asdfasdfsadf2343a$#QFdfsadf" ;

    @Autowired
    private SeckillDao seckillDao ;

    @Autowired
    private SuccessKillDao successKillDao ;

    public Seckill getById(Long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public List<Seckill> getAll() {
        return seckillDao.queryAll(4,0);
    }

    public Exposer exportSeckillUrl(Long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId) ;
        if(null == seckill)
            return new Exposer(false,seckillId) ;
        Date startTime = seckill.getStartTime() ;
        Date endTime = seckill.getEndTime() ;
        Date now = new Date() ;
        if(now.getTime() < startTime.getTime()
                || now.getTime() > endTime.getTime())
            return new Exposer(false,seckillId,now.getTime(),startTime.getTime(),endTime.getTime()) ;
        String md5 = getMD5(seckillId) ;
        return new Exposer(true,md5,seckillId);
    }

    private String getMD5(Long seckillId){
        String base = seckillId + "/" + slat ;
        return DigestUtils.md5DigestAsHex(base.getBytes()) ;
    }

    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws ReapeatkillExeception, SeckillExeception, SeckillCloseExeception {
        try {
            if(md5 == null || !md5.equals(getMD5(seckillId))){
                throw new SeckillExeception("seckill data rewrite") ;
            }
            Date nowDate = new Date() ;
            int updateCount = seckillDao.reduceNumber(seckillId,nowDate) ;
            if(updateCount <= 0)
                throw new SeckillCloseExeception("seckill is closed!");
            int insertCount = successKillDao.insert(seckillId,userPhone) ;
            if(insertCount <= 0)
                throw new ReapeatkillExeception("seckill repeated") ;
            SuccessKill successKill = successKillDao.queryByIdWithSeckill(seckillId,userPhone) ;
            return new SeckillExecution(SeckillEnums.SUCCESS,seckillId,successKill);
        }catch (ReapeatkillExeception e){
            logger.debug(e.getMessage(),e);
            throw new SeckillExeception(e.getMessage(),e) ;
        }catch (SeckillCloseExeception e){
            logger.debug(e.getMessage(),e);
            throw new SeckillExeception(e.getMessage(),e) ;
        }catch (Exception e){
            logger.debug(e.getMessage(),e);
            throw new SeckillExeception(e.getMessage(),e) ;
        }
    }
}
