package co.bluepx.edu.course.entity;

import co.bluepx.edu.core.codebuilder.TableSchemaSqlBuilder;
import co.bluepx.edu.core.model.BaseIncrementIdModel;
import co.bluepx.edu.member.entity.MemberType;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Course extends BaseIncrementIdModel {

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

    public int getIsPay() {
        return isPay;
    }

    public void setIsPay(int isPay) {
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

    public String getSellType() {
        return sellType;
    }

    public void setSellType(String sellType) {
        this.sellType = sellType;
    }

    public Long getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(Long recommendId) {
        this.recommendId = recommendId;
    }

    public String getTeacherIds() {
        return teacherIds;
    }

    public void setTeacherIds(String teacherIds) {
        this.teacherIds = teacherIds;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
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

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    private String name;//课程名称
    private Long isavaliable;//0可用(上架)1不可用(下架)
    private Date addtime;
    private int isPay;
    private java.math.BigDecimal sourceprice;//课程原价格（只显示）
    private java.math.BigDecimal currentprice;//课程销售价格（实际支付价格）设置为0则可免费观看
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
    private List<MemberType> memberTypes;//会员类型集合
    private Long pageBuycount;
    private Long pageViewcount;
    private String freeurl;
    private String sellType;//课程的销售方式,COURSE,PACKAGE
    private Long recommendId;//推荐分类模块id
    private String teacherIds;

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    public List<MemberType> getMemberTypes() {
        return memberTypes;
    }

    public void setMemberTypes(List<MemberType> memberTypes) {
        this.memberTypes = memberTypes;
    }

    private List<Teacher> teacherList;//该课程 下的老师list
    private Long subjectId;
    @DateTimeFormat(pattern = "yyyy-mm-dd hh-mm-ss")
    private Date liveBeginTime;//直播开始时间
    @DateTimeFormat(pattern = "yyyy-mm-dd hh-mm-ss")
    private Date liveEndTime;//直播结束时间

    private Long orderNum; // 排序值

    public static void main(String[] args) {

        System.out.println(new TableSchemaSqlBuilder().setPrefix("edu").buildByClass(Course.class));

    }

}
