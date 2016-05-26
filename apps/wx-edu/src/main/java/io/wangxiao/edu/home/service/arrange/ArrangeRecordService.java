
package io.wangxiao.edu.home.service.arrange;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.arrange.ArrangeRecord;

import java.util.List;

/**
 * ArrangeRecord管理接口
 */
public interface ArrangeRecordService {

    /**
     * 添加ArrangeRecord
     *
     * @param arrangeRecord 要添加的ArrangeRecord
     * @return id
     */
    java.lang.Long addArrangeRecord(ArrangeRecord arrangeRecord);
    /*public java.lang.Long addArrangeRecord(ArrangeRecord arrangeRecord);*/

    /**
     * 批量添加ArrangeRecord
     *
     * @param arrangeRecords
     */
    void batchAddArrangeTecord(List<ArrangeRecord> arrangeRecords);

    /**
     * 根据id删除一个ArrangeRecord
     *
     * @param id 要删除的id
     */
    void deleteArrangeRecordById(Long id);

    /**
     * 修改ArrangeRecord
     *
     * @param arrangeRecord 要修改的ArrangeRecord
     */
    void updateArrangeRecord(ArrangeRecord arrangeRecord);

    /**
     * 根据id获取单个ArrangeRecord对象
     *
     * @param id 要查询的id
     * @return ArrangeRecord
     */
    ArrangeRecord getArrangeRecordById(Long id);

    /**
     * 根据条件获取ArrangeRecord列表
     *
     * @param arrangeRecord 查询条件
     * @return List<ArrangeRecord>
     */
    List<ArrangeRecord> getArrangeRecordList(ArrangeRecord arrangeRecord, PageEntity page);

    List<ArrangeRecord> getArrangeRecordList(ArrangeRecord arrangeRecord);


    /**
     * 根据条件获取ArrangeRecord列表
     *
     * @param arrangeRecord 查询条件
     * @return arrangeRecord
     */
    ArrangeRecord getArrangeRecordByArrangeRecord(ArrangeRecord arrangeRecord);

    List<ArrangeRecord> getArrangeRecorduserId(Long userid);


    /**
     * 根据部门和任务ID查出分数
     */
    List<ArrangeRecord> getGroudArrange(ArrangeRecord arrangeRecord);

    /**
     * 删除任务下的记录
     *
     * @param taskId
     */
    void deleteArrangeRecoredId(Long taskId);

    /**
     * 根据部门和任务ID查出详细信息
     */
    List<ArrangeRecord> getArrangeRecordGroudUsers(ArrangeRecord arrangeRecord);

    /**
     * 根据taskid和userid查出id
     */
    ArrangeRecord getuserArrangeid(ArrangeRecord ArrangeRecord);

    /**
     * 根据userid查出部门（一个人对应一个部门的基础前提下）
     */
    ArrangeRecord getGrouduserid(Long userid);

    /**
     * 根据gruopid查出部门下有多少个任务(已完成)
     */
    Long getgruopArrange(Long groupid);

    /**
     * 根据gruopid查出部门下有多少个任务（总数）
     */
    Long getcountgruopArrange(Long groupid);

    /**
     * 根据ArrangeId查出该任务是否有效
     */
    Long getIscompleteArrange(Long groupid);

    /**
     * 提交考试安排修改考试记录信息
     *
     * @param arrangeRecord
     */
    void updateArrangeRecordBySubmit(ArrangeRecord arrangeRecord);

    /**
     * 获取ArrangeRecordAndArrange
     *
     * @param arrangeRecordId
     * @return
     */
    ArrangeRecord getArrangeRecordAndArrangeById(Long arrangeRecordId);


}
