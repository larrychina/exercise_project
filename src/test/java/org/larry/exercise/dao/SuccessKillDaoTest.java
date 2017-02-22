package org.larry.exercise.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.larry.exercise.entity.SuccessKill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Larry on 2017/2/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKillDaoTest {
    @Resource
    private SuccessKillDao successKillDao ;
    @Test
    public void insert() throws Exception {
        int result = successKillDao.insert(1005L,18550350912L) ;
        System.out.println("result:\t"+result);
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        SuccessKill successKill = successKillDao.queryByIdWithSeckill(1005L,18550350912L) ;
        System.out.println(successKill.toString());
    }

}