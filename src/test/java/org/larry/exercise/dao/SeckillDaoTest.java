package org.larry.exercise.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.larry.exercise.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Larry on 2017/2/22.
 * 配置spring 整合junit ,junit启动时加载springIOC容器
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    @Resource
    private SeckillDao seckillDao ;

    @Test
    public void reduceNumber() throws Exception {
        Date killTime = new Date() ;
        int updateCount = seckillDao.reduceNumber(1005L,killTime) ;
        System.out.println("result:\t"+updateCount);

    }

    @Test
    public void queryById() throws Exception {
        long id = 1005 ;
        Seckill seckill = seckillDao.queryById(id) ;
        System.out.println(seckill.getGoodsName());
    }

    @Test
    public void queryAll() throws Exception {
        List<Seckill> seckillList = seckillDao.queryAll(10,0) ;
        for (int i = 0; i < seckillList.size(); i++) {
            System.out.println(seckillList.get(i).getGoodsName()+"\t"+seckillList.get(i).getSeckillId()+"\t"+seckillList.get(i).getNumber());
        }
    }

}