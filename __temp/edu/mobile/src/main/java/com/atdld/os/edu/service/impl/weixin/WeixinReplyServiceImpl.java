package com.atdld.os.edu.service.impl.weixin;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.dao.weixin.WeixinReplyDao;
import com.atdld.os.edu.entity.weixin.QueryWeixinReply;
import com.atdld.os.edu.entity.weixin.WeixinManyImageDTO;
import com.atdld.os.edu.entity.weixin.WeixinReply;
import com.atdld.os.edu.entity.weixin.WeixinManyImage;
import com.atdld.os.edu.entity.weixin.WeixinSetReply;
import com.atdld.os.edu.service.weixin.WeixinReplyService;
import com.atdld.os.edu.service.weixin.WeixinSetService;



@Service("weixinReplyService")
public class WeixinReplyServiceImpl implements WeixinReplyService {
	@Autowired
    private WeixinReplyDao weixinDao;

	@Autowired
    private WeixinSetService weixinSetService;
	public Long addWeixin(WeixinReply weixin) {
		
		return weixinDao.addWeixin(weixin);
	}

	
	public void updateWeixinReply(WeixinReply weixin) {
		weixinDao.updateWeixinReply(weixin);
		
	}

	
	public void deleteWeixin(Long wxId) {
		
		weixinDao.deleteWeixin(wxId);
	}

	
	public WeixinReply getWeixinById(Long wxId) {
		
		return weixinDao.getWeixinById(wxId);
	}

	
	public List<WeixinReply> getWeixinByMsgType(String msgType) {
		
		return weixinDao.getWeixinByMsgType(msgType);
	}

	
	public List<WeixinReply> getWeixinReplyPageList(QueryWeixinReply queryWeixinReply,PageEntity page) {
		
		return weixinDao.getWeixinReplyPageList(queryWeixinReply,page);
	}

	
	public List<WeixinReply> getImageTextReplyPageList(QueryWeixinReply queryWeixinReply,PageEntity page) {
		
		return weixinDao.getImageTextReplyPageList(queryWeixinReply, page);
	}

	
	public void addWeixinManyImageText(Long manyImageId,String ids) {
		List<WeixinManyImage> imageTextList=new ArrayList<WeixinManyImage>();
		String[] imageArray=ids.replace(",", " ").trim().split(" ");
		for(int i=0;i<imageArray.length;i++)//从属图文数组
		{
			WeixinManyImage weixinManyImage=new WeixinManyImage();//多图文和从属图文中间表
			weixinManyImage.setManyImageId(manyImageId);//多图文id
			weixinManyImage.setImageId(Long.parseLong(imageArray[i]));//从属图文id
			imageTextList.add(weixinManyImage);
		}
		weixinDao.addWeixinManyImageText(imageTextList);
	}

	
	public void delWeixinManyImageTextByManyId(Long id) {
		weixinDao.delWeixinManyImageTextByManyId(id);
		
	}

	
	public void delWeixinManyImageTextById(Long id) {
		
		weixinDao.delWeixinManyImageTextById(id);
	}

	
	public List<WeixinManyImageDTO> getWeixinManyImageTextByManyId(Long id) {
		
		return weixinDao.getWeixinManyImageTextByManyId(id);
	}

	public List<WeixinReply> getWeixinBywxKeywords(String eventKey) {
		
		return weixinDao.getWeixinBywxKeywords(eventKey);
	}

	/**
	 * 查询子图文
	 * @param id
	 * @return
	 */
	public List<WeixinReply> getWeixinReplyByManyId(Long id){
		return weixinDao.getWeixinReplyByManyId(id);
	}
	/**
	 *关键字回复
	 */
	public WeixinReply WeixinExit(String keyWords,String fromUserName,String toUserName)
	{
		WeixinReply weixinReply=getWeixinByEventKey(keyWords);//根据关键字找到回复
		return weixinReply;
	}
	/**
	 * 查询常规微信回复
	 * @return
	 */
	public WeixinReply getDefaultWeixin(String type)
	{
		WeixinSetReply weixinSetReply=weixinSetService.getWeixinSetReply(type);//查询常规回复设置
		if(weixinSetReply==null)
		{
			return null;
		}
		WeixinReply defaultWeixin=getWeixinById(weixinSetReply.getReplyId());//获得回复素材
		return defaultWeixin;
	}
	/**
	 * 根据关键字精确查找回复
	 * @param eventKey
	 * @return
	 */
	public WeixinReply getWeixinByEventKey(String eventKey)
	{
		List<WeixinReply> weixins=getWeixinBywxKeywords(eventKey);
		for(WeixinReply clickWeixin:weixins)
		{
			String[] keyWords=clickWeixin.getKeyword().split(" ");
			for(String keyWord:keyWords)
			{
				if(keyWord.equals(eventKey))
				{
					return clickWeixin;
				}
			}
		}
		return null;
	}
	
		
}
