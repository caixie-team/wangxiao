package io.wangxiao.edu.home.service.weixin;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.weixin.QueryWeixinReply;
import io.wangxiao.edu.home.entity.weixin.WeixinManyImageDTO;
import io.wangxiao.edu.home.entity.weixin.WeixinReply;

import java.util.List;


public interface WeixinReplyService {
    /**
     * 添加微信文本或图文回复素材
     *
     * @param weixin
     * @return
     */
    Long addWeixin(WeixinReply weixin);

    /**
     * 更新微信回复信息（文本或图文）素材
     *
     * @param weixin
     */
    void updateWeixinReply(WeixinReply weixin);

    /**
     * 根据id删除微信素材
     *
     * @param id
     */
    void deleteWeixin(Long id);

    /**
     * 根据id查询一条记录
     *
     * @param id
     * @return
     */
    WeixinReply getWeixinById(Long id);

    /**
     * 根据类型查询
     *
     * @param msgType
     * @return
     */
    List<WeixinReply> getWeixinByMsgType(String msgType);

    /**
     * 微信回复分页
     *
     * @param queryWeixinReply
     * @return
     */
    List<WeixinReply> getWeixinReplyPageList(QueryWeixinReply queryWeixinReply, PageEntity page);

    /**
     * 微信图文回复分页
     *
     * @param queryWeixinReply
     * @return
     */
    List<WeixinReply> getImageTextReplyPageList(QueryWeixinReply queryWeixinReply, PageEntity page);

    /**
     * 微信多图文和图文关联
     *
     * @param imageTextList
     */
    void addWeixinManyImageText(Long mangImageId, String ids);

    /**
     * 微信根据多图文ID删除多图文和图文关联
     *
     * @param id
     */
    void delWeixinManyImageTextByManyId(Long id);

    /**
     * 微信根据图文ID删除多图文和图文关联
     *
     * @param id
     */
    void delWeixinManyImageTextById(Long id);

    /**
     * 微信根据多图文ID查询多图文和图文关联
     *
     * @param id
     * @return
     */
    List<WeixinManyImageDTO> getWeixinManyImageTextByManyId(Long id);

    /**
     * 根据根据关键字模糊查询微信回复
     *
     * @param eventKey
     * @return
     */
    List<WeixinReply> getWeixinBywxKeywords(String eventKey);

    /**
     * 查询子图文
     *
     * @param id
     * @return
     */
    List<WeixinReply> getWeixinReplyByManyId(Long id);

    /**
     * 关键字回复
     */
    WeixinReply WeixinExit(String keyWords, String fromUserName, String toUserName);

    /**
     * 查询常规微信回复
     *
     * @return
     */
    WeixinReply getDefaultWeixin(String type);

    /**
     * 根据关键字精确查找回复
     *
     * @param eventKey
     * @return
     */
    WeixinReply getWeixinByEventKey(String eventKey);

    /**
     * 根据ids获取list
     *
     * @param ids
     * @return
     */
    List<WeixinReply> getWeixinManyImageByIds(String ids);


}


