package co.bluepx.edu.course.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class QueryCourse implements Serializable{
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIsavaliable() {
        return isavaliable;
    }

    public void setIsavaliable(Long isavaliable) {
        this.isavaliable = isavaliable;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    public BigDecimal getSourceprice() {
        return sourceprice;
    }

    public void setSourceprice(BigDecimal sourceprice) {
        this.sourceprice = sourceprice;
    }

    public BigDecimal getCurrentprice() {
        return currentprice;
    }

    public void setCurrentprice(BigDecimal currentprice) {
        this.currentprice = currentprice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Long getLessionnum() {
        return lessionnum;
    }

    public void setLessionnum(Long lessionnum) {
        this.lessionnum = lessionnum;
    }

    public String getCoursetag() {
        return coursetag;
    }

    public void setCoursetag(String coursetag) {
        this.coursetag = coursetag;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getMobileLogo() {
        return mobileLogo;
    }

    public void setMobileLogo(String mobileLogo) {
        this.mobileLogo = mobileLogo;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getLosetype() {
        return losetype;
    }

    public void setLosetype(int losetype) {
        this.losetype = losetype;
    }

    public Date getLoseAbsTime() {
        return loseAbsTime;
    }

    public void setLoseAbsTime(Date loseAbsTime) {
        this.loseAbsTime = loseAbsTime;
    }

    public String getLoseTime() {
        return loseTime;
    }

    public void setLoseTime(String loseTime) {
        this.loseTime = loseTime;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }

    public Long getMembertype() {
        return membertype;
    }

    public void setMembertype(Long membertype) {
        this.membertype = membertype;
    }

    public Long getPageBuycount() {
        return pageBuycount;
    }

    public void setPageBuycount(Long pageBuycount) {
        this.pageBuycount = pageBuycount;
    }

    public Long getPageViewcount() {
        return pageViewcount;
    }

    public void setPageViewcount(Long pageViewcount) {
        this.pageViewcount = pageViewcount;
    }

    public String getFreeurl() {
        return freeurl;
    }

    public void setFreeurl(String freeurl) {
        this.freeurl = freeurl;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getSellType() {
        return sellType;
    }

    public void setSellType(String sellType) {
        this.sellType = sellType;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getLiveBeginTime() {
        return liveBeginTime;
    }

    public void setLiveBeginTime(Date liveBeginTime) {
        this.liveBeginTime = liveBeginTime;
    }

    public Date getLiveEndTime() {
        return liveEndTime;
    }

    public void setLiveEndTime(Date liveEndTime) {
        this.liveEndTime = liveEndTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private Long id;
    private Long subjectId;
    private String name;
    private Long isavaliable;
    private Date addtime;
    private Integer isPay;
    private java.math.BigDecimal sourceprice;
    private java.math.BigDecimal currentprice;
    private String title;
    private String context;
    private Long lessionnum;
    private String coursetag;
    private String logo;
    private String mobileLogo;
    private Date updateTime;
    private int losetype;
    private Date loseAbsTime;
    private String loseTime;
    private String updateuser;
    private Long membertype;
    private Long pageBuycount;
    private Long pageViewcount;
    private String freeurl;
    private Long teacherId;//查询课程传入教师id
    private int order;//查询课程传入排序标识
    private String sellType;//课程的销售方式,COURSE,PACKAGE
    private List<Teacher> teacherList;//该课程 下的老师list
    private Long userId;		//学员id
    @DateTimeFormat(pattern="yyyy-mm-dd hh-mm-ss")
    private Date liveBeginTime;//直播开始时间
    @DateTimeFormat(pattern="yyyy-mm-dd hh-mm-ss")
    private Date liveEndTime;//直播结束时间
    private int status;//直播状态 1未开始 2进行中 3已结束 
}
