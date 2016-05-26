package io.wangxiao.edu.home.service.impl.letter;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.letter.MsgSystemDao;
import io.wangxiao.edu.home.entity.letter.MsgSystem;
import io.wangxiao.edu.home.service.letter.MsgReceiveService;
import io.wangxiao.edu.home.service.letter.MsgSenderService;
import io.wangxiao.edu.home.service.letter.MsgSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @description 系统消息
 */
@Service("msgSystemService")
public class MsgSystemServiceImpl implements MsgSystemService {

    @Autowired
    private MsgSystemDao msgSystemDao;
    @Autowired
    private MsgReceiveService msgReceiveService;
    @Autowired
    private MsgSenderService msgSenderService;

    public Long addMsgSystem(MsgSystem msgSystem) throws Exception {
        return msgSystemDao.addMsgSystem(msgSystem);
    }

    /**
     * 批量添加系统消息
     *
     * @param msgSystemList 消息的list
     */
    public void addMsgSystemBatch(List<MsgSystem> msgSystemList) {
        msgSystemDao.addMsgSystemBatch(msgSystemList);
    }

    /**
     * 查询系统消息
     *
     * @param msgSystem
     * @return
     * @throws Exception
     */
    public List<MsgSystem> queryMsgSystemList(MsgSystem msgSystem, PageEntity page) throws Exception {
        return msgSystemDao.queryMsgSystemList(msgSystem, page);
    }

    /**
     * 通过id删除系统消息
     *
     * @param msgSystem
     * @return
     * @throws Exception
     */
    public Long delMsgSystemById(Long id) throws Exception {
        return msgSystemDao.delMsgSystemById(id);
    }

    /**
     * 查询大于传入的时间的系统系统消息
     *
     * @param msgSystem
     * @return
     * @throws Exception
     */
    public List<MsgSystem> queryMSListByLT(Date lastTime) throws Exception {
        return msgSystemDao.queryMSListByLT(lastTime);
    }

    /**
     * 检查系统消息过期更新字段 删除过期的站内信
     *
     * @param msgSystem
     * @return
     * @throws Exception
     */
    public void updatePast() throws Exception {
        System.out.println("**********************updatePast***************");
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) - 180);
        Date endDate = dft.parse(dft.format(date.getTime()));
        msgReceiveService.delMsgReceivePast(endDate);
        msgSenderService.delMsgSenderPast(endDate);
        msgSystemDao.updateMsgSystemPastTime(endDate);

    }


}
