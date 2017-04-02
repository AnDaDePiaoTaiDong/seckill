package org.seckill.dto;

import java.util.Date;

/**
 * Created by xudong on 2017/3/25.
 */
public class Exposer {
    private long seckillId;
    private boolean exposed;
    private String md5;
    private Date now;
    private Date start;
    private Date end;

    public Exposer(boolean exposed,long seckillId, Date now, Date start, Date end) {
        this.seckillId=seckillId;
        this.exposed = exposed;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposer(boolean exposed, String md5, long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposed,long seckillId) {
        this.seckillId = seckillId;
        this.exposed = exposed;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "seckillId=" + seckillId +
                ", exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
