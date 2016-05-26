package io.wangxiao.edu.home.service.arrange;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.arrange.Arrange;

import java.util.List;

/**
 * Arrange管理接口
 */
public interface ArrangeService {

    /**
     * 添加Arrange
     *
     * @param arrange 要添加的Arrange
     * @return id
     */
    java.lang.Long addArrange(Arrange arrange);

    boolean arrange(Arrange arrange);

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
     * 我的部门任务
     *
     * @param arrange
     * @param page
     * @return
     */
    List<Arrange> getGroupArrangeList(Arrange arrange, PageEntity page);

    /**
     * 任务列表详情
     *
     * @param arrange
     * @param page
     * @return
     */
    List<Arrange> arrangeDetailsById(Arrange arrange, PageEntity page);

    /**
     * 更改任务状态
     */
    void updateArrangeStatus(Arrange arrange);

    /**
     * 前台我的任务
     *
     * @param arrange
     * @param page
     * @return
     */
    List<Arrange> myArrangeForWeb(Arrange arrange, PageEntity page);

    /**
     * 前台我的部门任务
     *
     * @param arrange
     * @param page
     * @return
     */
    List<Arrange> myGroupArrangeFroweb(Arrange arrange, PageEntity page);

    /**
     * 开启任务
     *
     * @param id
     */
    void addStartArrange(Long id);

    /**
     * 定时结束考试
     */
    void timingEndExam();

}