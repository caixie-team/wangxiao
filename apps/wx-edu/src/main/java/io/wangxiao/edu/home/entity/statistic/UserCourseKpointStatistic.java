package io.wangxiao.edu.home.entity.statistic;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserCourseKpointStatistic implements Serializable {
    private Long id;
    private java.util.Date statisticDate;
    private String date;
    private Long playCount = 0L;//播放次数
    private Long playerNum = 0L;//观看人数
}
