package io.wangxiao.order.entity;

import io.wangxiao.course.entity.Course;
import org.beetl.sql.core.TailBean;
import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;

@Table(name="edu_sho")
public class Shopcart extends TailBean {
    public Long getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Long goodsid) {
        this.goodsid = goodsid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    private Long goodsid;// 商品id
    private Long userid;
    private Long type;//1课程 2套餐（备用）
    private java.util.Date addTime;
    private Course course;
}
