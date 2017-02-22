package org.larry.exercise.dto;

/**
 * Created by Larry on 2017/2/22.
 * 暴露秒杀接口
 */
public class Exposer {

    // 秒杀是否开始
    private Boolean exposed ;

    private String md5 ;

    private Long seckillId ;

    // 系统当前时间
    private Long now ;

    // 开始时间
    private Long start ;

    // 结束时间
    private Long end ;

    public Exposer(Boolean exposed, String md5, Long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public Exposer(Boolean exposed, Long seckillId, Long now, Long start, Long end) {
        this.exposed = exposed;
        this.seckillId = seckillId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposer(Boolean exposed, Long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    public Boolean getExposed() {
        return exposed;
    }

    public void setExposed(Boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public Long getNow() {
        return now;
    }

    public void setNow(Long now) {
        this.now = now;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }
}
