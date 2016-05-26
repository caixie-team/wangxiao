package io.wangxiao.edu.app.entity.website;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @description 推荐课程DTO
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppWebsiteCourseDetailDTO implements Serializable {

    private Long id;// 主键
    private Long recommendId;// 分类id
    private Long courseId;// 课程id
    private int orderNum;// 排序值
    private String recommendName;//推荐名称
    private String courseName;//课程名称
    private int isavaliable;//课程是否下架
    private String logo;//课程图片
}
