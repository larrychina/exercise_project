package org.larry.exercise.dto;

import org.larry.exercise.entity.SuccessKill;
import org.larry.exercise.enums.SeckillEnums;

/**
 * Created by Larry on 2017/2/22.
 * 执行秒杀结果
 */
public class SeckillExecution {


    // 秒杀执行状态
    private int state ;

    private String stateInfo ;

    private SeckillEnums stateEnum ;

    private Long seckillId ;

    private SuccessKill successKill ;

    public SeckillExecution(int state, String stateInfo, Long seckillId) {
        this.state = state;
        this.stateInfo = stateInfo;
        this.seckillId = seckillId;
    }

    public SeckillExecution(SeckillEnums stateEnum, Long seckillId) {
        this.stateEnum = stateEnum;
        this.stateInfo = stateEnum.getStateInfo() ;
        this.state = stateEnum.getState() ;
        this.seckillId = seckillId;
    }

    public SeckillExecution(SeckillEnums state, Long seckillId, SuccessKill successKill) {
        this.state = state.getState();
        this.stateInfo = state.getStateInfo();
        this.seckillId = seckillId;
        this.successKill = successKill;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public SuccessKill getSuccessKill() {
        return successKill;
    }

    public void setSuccessKill(SuccessKill successKill) {
        this.successKill = successKill;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", seckillId=" + seckillId +
                ", successKill=" + successKill +
                '}';
    }
}
