package io.wangxiao.edu.home.dao.arrange;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.arrange.Arrange;

import java.util.Date;
import java.util.List;

/**
 * Arrange管理接口
 */
public interface ArrangeDao {

    /**
     * 添加Arrange
     *
     * @param arrange 要添加的Arrange
     * @return id
     */
    java.lang.Long addArrange(Arrange arrange);

    /**
     * 根据id删除一个Arrange
     *
     * @param id 要删除的id
     */
    void deleteArrangeById(Long id);

    /**
     * 修改Arrange
     *
     * @param arrange 要修改的Arrange
     */
    java.lang.Long updateArrange(Arrange arrange);

    /**
     * 根据id获取单个Arrange对象
     *
     * @param id 要查询的id
     * @return Arrange
     */
    Arrange getArrangeById(Long id);

    /**
     * 根据条件获取Arrange列表
     *
     * @param arrange 查询条件
     * @return List<Arrange>
     */
    List<Arrange> getArrangeList(Arrange arrange, PageEntity page);

    /**
     * 我的部门安排考试
     *
     * @param arrange
     * @param page
     * @return
     */
    List<Arrange> getGroupArrangeList(Arrange arrange, PageEntity page);

    /**
     * 安排考试列表详情
     *
     * @param arrange
     * @param page
     * @return
     */
    List<Arrange> arrangeDetailsById(Arrange arrange, PageEntity page);

    /**
     * 更改安排考试状态
     */
    void updateArrangeStatus(Arrange arrange);

    /**
     * 前台我的安排考试
     *
     * @param userId
     * @param page
     * @return
     */
    List<Arrange> myArrangeForWeb(Arrange arrange, PageEntity page);

    /**
     * 前台我的部门安排考试
     *
     * @param arrange
     * @param page
     * @return
     */
    List<Arrange> myGroupArrangeFroweb(Arrange arrange, PageEntity page);

    /**
     * 获取小于今日的考试任务
     *
     * @param date
     */
    List<Arrange> getArrangeListByTime(Date date);

    /**
     * 修改考试任务结束时间小于当前时间的安排状态为已结束
     *
     * @param date
     */
    void updateArrangeListByTime(Date date);
}