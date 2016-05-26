package io.wangxiao.edu.home.service.impl.letter;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.letter.MsgSenderDao;
import io.wangxiao.edu.home.entity.letter.MsgSender;
import io.wangxiao.edu.home.entity.user.UserExpandDto;
import io.wangxiao.edu.home.service.letter.MsgSenderService;
import io.wangxiao.edu.home.service.user.UserExpandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author : xiaokun
 * @ClassName com.business.platform.sns.service.impl.letter.MsgSenderServiceImpl
 * @description 站内信的发件箱service的实现
 * @Create Date : 2014-1-26 下午1:55:11
 */
@Service("msgSenderService")
public class MsgSenderServiceImpl implements MsgSenderService {

    @Autowired
    private MsgSenderDao msgSendwerDao;

    @Autowired
    private UserExpandService userExpandService;

    /**
     * 添加站内信发件箱
     *
     * @param msgSender 站内信实体
     * @throws Exception
     */
    public Long addMsgSender(MsgSender msgSender) throws Exception {
        return msgSendwerDao.addMsgSender(msgSender);
    }

    /**
     * 查询站内信发件箱
     *
     * @param MsgSender 站内信发件箱实体
     * @param page      分页参数
     * @return List<QueryLetter> 站内信的list
     * @throws Exception
     */
    public List<MsgSender> queryMsgSenderByOutbox(MsgSender msgSender, PageEntity page) throws Exception {
        List<MsgSender> msgSenderList = msgSendwerDao.queryMsgSenderByOutbox(msgSender, page);// 查询站内信发件箱

        Map<String, UserExpandDto> map = userExpandService.getUserExpandByUids(getReceivingCusId(msgSenderList));// 查询用户的信息
        if (msgSenderList != null && msgSenderList.size() > 0) {
            for (MsgSender msgSender1 : msgSenderList) {
                UserExpandDto userExpandDto = map.get(msgSender1.getReceivingCusId() + "");// 查询用户的信息
                if (userExpandDto != null) {// 如果能够查到则set 头像信息
                    msgSender1.setUserExpandDto(userExpandDto);
                }
            }
        }
        return msgSenderList;// 查询站内信发件箱
    }

    public String getReceivingCusId(List<MsgSender> list) {// 获得用户ids
        String ids = "";
        if (list != null && list.size() > 0) {
            for (MsgSender msgSender : list) {
                ids += msgSender.getReceivingCusId() + ",";
            }
        }
        return ids;
    }

    /**
     * 删除发件箱
     *
     * @param msgReceive 站内信实体 通过站内信的id
     * @throws Exception
     */
    public Long delLetterOutbox(MsgSender msgSender) throws Exception {
        return msgSendwerDao.delLetterOutbox(msgSender);// 更新站内信的状态 删除发件箱
    }

    /**
     * 删除站内信过期消息
     */
    public Long delMsgSenderPast(Date time) throws Exception {
        return msgSendwerDao.delMsgSenderPast(time);
    }

    /**
     * 清空站内信发件箱
     */
    public Long delAllOutbox(Long cusId) throws Exception {
        return msgSendwerDao.delAllOutbox(cusId);
    }

    /**
     * 删除传入的ids
     */
    public Long delMsgSenderByids(String ids) throws Exception {
        return msgSendwerDao.delMsgSenderByids(ids);
    }
}
