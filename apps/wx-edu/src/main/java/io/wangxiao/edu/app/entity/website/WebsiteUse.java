package io.wangxiao.edu.app.entity.website;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 使用统计
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WebsiteUse implements Serializable {
    private Long id;
    private String ip;//ip
    private String brand;//品牌
    private String type;//型号
    private String size;//尺寸
    @DateTimeFormat(pattern = "yyyy-mm-dd hh-mm-ss")
    private Date createTime;//添加时间
    private Long userId;
    @DateTimeFormat(pattern = "yyyy-mm-dd hh-mm-ss")
    private Date beginTime;//使用开始时间
    @DateTimeFormat(pattern = "yyyy-mm-dd hh-mm-ss")
    private Date endTime;//使用结束时间
    private String email;//邮箱
    @DateTimeFormat(pattern = "yyyy-mm-dd hh-mm-ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-mm-dd hh-mm-ss")
    private Date overTime;

    private int beginFalg;//1 开始使用的时候调用 2 结束的时候调用
}
