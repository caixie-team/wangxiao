package io.wangxiao.edu.home.entity.course;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class CourseKpoint implements Serializable {
    private Long id;//节点id
    private Long courseId;//课程id
    private String name;//节点名称
    private Long parentId;
    private Long type;//类型 0课时节点1是章节
    private Long status;//状态0正常1删除
    private java.util.Date addTime;//添加时间
    private Long sort;//排序
    private Long playcount = 0L;//播放次数
    private Long lookNumber = 0L;//观看人数
    private Long playTime = 0L;// 播放时长(总计)
    private Long isfree;//是否可以试听1免费2收费
    private String videotype;//视频来源：CC,56,LS,mp4flv
    private String videourl;//视频地址
    private Long teacherId;//讲师id
    private Long courseMinutes; //视频时长 分
    private String videojson;//json格式辅助56
    private Long courseSeconds; //视频时长 秒
    private Long personNum;
    private String courseName;
    private String courseType;//上传格式：图文,mp3,pdf
    private String courseText;//内容
    private List<CourseKpoint> childKpoints = new ArrayList<CourseKpoint>();
    private String parentName;

    public String getUrl() {
        if (videourl.indexOf("http://") != -1 || videourl.indexOf("https://") != -1) {
            return videourl;
        }
        return null;
    }
}
