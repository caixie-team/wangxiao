package io.wangxiao.edu.home.dao.impl.letter;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.letter.MsgSystemDao;
import io.wangxiao.edu.home.entity.letter.MsgSystem;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description 站内信发件箱的Dao 实现
 */
@Repository("MsgSystemDao")
public class MsgSystemDaoImpl extends GenericDaoImpl implements MsgSystemDao {
    /**
     * 添加系统消息
     *
     * @param msgSender
     * @return
     * @throws Exception
     */
    public Long addMsgSystem(MsgSystem msgSystem) throws Exception {
        return this.insert("MsgSystemMapper.addMsgSystem", msgSystem);
    }

    /**
     * 批量添加系统消息
     *
     * @param msgSystemList 消息的list
     */
    public void addMsgSystemBatch(List<MsgSystem> msgSystemList) {
        this.insert("MsgSystemMapper.addMsgSystemBatch", msgSystemList);
    }

    /**
     * 查询系统消息
     *
     * @param msgSystem
     * @return
     * @throws Exception
     */
    public List<MsgSystem> queryMsgSystemList(MsgSystem msgSystem, PageEntity page) throws Exception {
        return this.queryForListPage("MsgSystemMapper.queryMsgSystemList", msgSystem, page);
    }

    /**
     * 通过id删除系统消息
     *
     * @param msgSystem
     * @return
     * @throws Exception
     */
    public Long delMsgSystemById(Long id) throws Exception {
        return this.update("MsgSystemMapper.delMsgSystemById", id);
    }

    /**
     * 查询大于传入的时间的系统系统消息
     *
     * @param msgSystem
     * @return
     * @throws Exception
     */
    public List<MsgSystem> queryMSListByLT(Date lastTime) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("lastTime", lastTime);
        return this.selectList("MsgSystemMapper.queryMSListByLT", map);
    }

    /**
     * 更新过期的系统消息的字段为过期
     */
    public void updateMsgSystemPastTime(Date lastTime) throws Exception {
        this.update("MsgSystemMapper.updateMsgSystemPastTime", lastTime);
    }
}
