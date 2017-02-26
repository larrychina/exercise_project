package org.larry.exercise.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.larry.exercise.dto.Exposer;
import org.larry.exercise.dto.SeckillExecution;
import org.larry.exercise.entity.Seckill;
import org.larry.exercise.exeception.SeckillExeception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Larry on 2017/2/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-*.xml"})
public class SeckillServiceTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass()) ;

    @Autowired
    private SeckillService seckillService ;
    @Test
    public void getById() throws Exception {
        Seckill seckill = seckillService.getById(1004L) ;
        logger.info("seckill={}",seckill);
    }

    @Test
    public void getAll() throws Exception {
        List<Seckill> seckillList = seckillService.getAll() ;
        logger.info("list={}",seckillList);
    }

    @Test
    public void exportSeckillUrl() throws Exception {
        Exposer exposer = seckillService.exportSeckillUrl(1012L) ;
        logger.info("md5={}",exposer.getMd5());
        logger.info("state={}",exposer.getExposed());
        // md5:5c7be2fdadbcb626b0881e321669cc93
    }

    @Test
    public void executeSeckill() throws Exception {
        long id = 1004L ;
        long userPhone = 18550235912L ;
        SeckillExecution seckillExecution  = seckillService.executeSeckill(id,userPhone,"5c7be2fdadbcb626b0881e321669cc93") ;
        logger.info("seckillExecution={}",seckillExecution);

    }

    @Test
    public void executeSeckillByProcedure() throws Exception {
        long id = 1012L ;
        long userPhone = 18550235912L ;
        SeckillExecution seckillExecution  = seckillService.executeSeckill(id,userPhone,"5c7be2fdadbcb626b0881e321669cc93") ;
        logger.info("seckillExecution={}",seckillExecution);

    }

}