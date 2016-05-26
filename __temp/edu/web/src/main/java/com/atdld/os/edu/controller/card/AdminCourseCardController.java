package com.atdld.os.edu.controller.card;

import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.atdld.os.common.constants.CommonConstants;
import com.atdld.os.common.contoller.SingletonLoginUtils;
import com.atdld.os.common.util.FileExportImportUtil;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.DateUtils;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.constants.enums.CardStatus;
import com.atdld.os.edu.controller.course.AdminCourseAssessController;
import com.atdld.os.edu.entity.card.Card;
import com.atdld.os.edu.entity.card.CardCode;
import com.atdld.os.edu.entity.card.CourseCardDTO;
import com.atdld.os.edu.entity.card.MainCardDTO;
import com.atdld.os.edu.entity.card.QueryCardCode;
import com.atdld.os.edu.entity.card.QueryMainCard;
import com.atdld.os.edu.entity.order.QueryTrxorder;
import com.atdld.os.edu.service.card.CardCodeService;
import com.atdld.os.edu.service.card.CardService;
import com.atdld.os.sysuser.entity.SysUser;

/**
 * @author
 * @version 2014年9月24日 下午3:33:04 课程卡信息管理
 */
@Controller
@RequestMapping("/admin")
public class AdminCourseCardController extends EduBaseController {
	private static final Logger logger = LoggerFactory.getLogger(AdminCourseAssessController.class);

	private String toCourseCardList = getViewPath("/admin/card/admin_card_list");// 课程卡列表
	private String toCreateCard = getViewPath("/admin/card/card_create");// 创建课程卡页面
	private String toCourseMainList = getViewPath("/admin/card/main_card_list");

    private String toCreateUserCard = getViewPath("/admin/card/card_user_create");// 创建学员卡

	@Autowired
	private CardService cardService;
	@Autowired
	private CardCodeService cardCodeService;

	// 绑定变量名字和属性，参数封装进类
	@InitBinder("card")
	public void initBinderUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("card.");
	}

	// 绑定变量名字和属性，参数封装进类
	@InitBinder("queryCardCode")
	public void initBinderCardCode(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("queryCardCode.");
	}
	
	
	// 绑定变量名字和属性，参数封装进类
	@InitBinder("queryMainCard")
	public void initBinderMainCard(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("queryMainCard.");
	}

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("/card/tocreatecard")
	public ModelAndView toCreateCard(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(toCreateCard);
		System.out.println(request.getParameter("addtype"));
		modelAndView.addObject("addType", request.getParameter("addtype"));
		return modelAndView;
	}

	/**
	 * 创建课程卡
	 * 
	 * @return
	 */
	@RequestMapping("/card/createcard")
	public String createCourseCard(HttpServletRequest request, @ModelAttribute("card") Card card) {
		try {
			String courseIds = request.getParameter("courseIds");
			JsonObject user =  SingletonLoginUtils.getSysUser(request);
			card.setCreateTime(new Date());
			card.setCreateUser(user.get("userName").toString());
			// 创建课程卡操作
			cardService.saveCourseCardInfo(card, courseIds);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("createCourseCard" + e);
		}
		return "redirect:/admin/card/cardlist"; // 重定向
	}

	/**
	 * 课程卡列表
	 * 
	 * @return
	 */
	@RequestMapping("/card/cardlist")
	public ModelAndView courseCardList(HttpServletRequest reqeust, @ModelAttribute("page") PageEntity page, @ModelAttribute QueryCardCode queryCardCode) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(toCourseCardList);
		try {
			// 设置分页 ，默认每页10
			this.setPage(page);
			List<CourseCardDTO> cardCodeList = cardService.getCardListByCondtion(queryCardCode, page);
			modelAndView.addObject("cardCodeList", cardCodeList);
			modelAndView.addObject("page", page);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("CardMainAction.saveCourseCardInfo", e);
		
		}
		return modelAndView;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/card/closemaincard/{id}")
	public Map<String,Object> closeMainCard(HttpServletRequest request,@PathVariable("id") Long id){
		try {
			cardCodeService.closeMainCard(id);
			this.setJson(true, "true", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	
	
	/**
	 * 课程卡主卡信息列表
	 * @param request
	 * @param page
	 * @param queryMainCard
	 * @return
	 */
	@RequestMapping("/card/mainlist")
	public ModelAndView courseCardMainList(HttpServletRequest request,@ModelAttribute("page") PageEntity page, @ModelAttribute QueryMainCard queryMainCard){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(toCourseMainList);
		try {
			this.setPage(page);
			List<MainCardDTO>  mainCardList = cardService.getMianListByCondition(queryMainCard, page);
			modelAndView.addObject("mainCardList", mainCardList);
			modelAndView.addObject("pagew", page);
		} catch (Exception e) {
			logger.error("courseCardMainList", e);
		}
		return modelAndView;
	}
	
	
	/**
	 * 课程卡状态修改
	 * @param id
	 * @return
	 */
	@RequestMapping("/card/closecard")
	@ResponseBody
	public Map<String,Object> updateCardCode(HttpServletRequest request){
		try {
			Long cardCodeId = new Long(request.getParameter("id"));
			CardCode cardCode = cardCodeService.getCardCodeById(cardCodeId);
			cardCode.setStatus(CardStatus.CLOSE.toString());
			cardCodeService.updateCardCode(cardCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * 课程卡信息导出
	 */
	@RequestMapping("/card/exportCard")
	public void exportCard(HttpServletRequest request, HttpServletResponse response, @ModelAttribute QueryCardCode queryCardCode) {
		try {
			//指定文件生成路径
			String dir = request.getSession().getServletContext().getRealPath("/excelfile/order");
			//文件名
			String expName="课程卡_" + DateUtils.getStringDateShort();
			// 表头信息
	        String[] headName = { "ID", "课程卡名称","类型","金额","有效开始时间","有效结束时间","课程卡号","密码","状态","订单号","用户邮箱","创建时间","创建人"};
	        //拆分为一万条数据每Excel，防止内存使用太大
			PageEntity page=new PageEntity();
			page.setPageSize(10000);
			cardService.getCardListByCondtion(queryCardCode, page);
			int num=page.getTotalPageSize();//总页数
			List<File> srcfile = new ArrayList<File>();//生成的excel的文件的list
			for(int i=1;i<=num;i++){//循环生成num个xls文件
				page.setCurrentPage(i);
				List<CourseCardDTO> cardCodeList = cardService.getCardListByCondtion(queryCardCode, page);
				List<List<String>> list=cardJoint(cardCodeList);
				File file = FileExportImportUtil.createExcel(headName, list, expName+"_"+i,dir);
				srcfile.add(file);
			}
	        FileExportImportUtil.createRar(response, dir, srcfile, expName);//生成的多excel的压缩包
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 卡编码excel格式拼接
	 * @param couponCodes
	 * @return
	 */
	public List<List<String>> cardJoint(List<CourseCardDTO> cardCodeList){
		List<List<String>> list = new ArrayList<List<String>>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < cardCodeList.size(); i++) {
			List<String> small = new ArrayList<String>();
			small.add(cardCodeList.get(i).getId()+"");
			small.add(cardCodeList.get(i).getName());
			if(cardCodeList.get(i).getType()==1){
				small.add("课程卡");
			}else if (cardCodeList.get(i).getType()==2){
				small.add("充值卡");
			}else if (cardCodeList.get(i).getType()==3){
				small.add("学员卡");
			}
			
			small.add(cardCodeList.get(i).getMoney()+"");
			if (cardCodeList.get(i).getType()==1 || cardCodeList.get(i).getType()==2) {
				small.add(format.format(cardCodeList.get(i).getBeginTime()));
				small.add(format.format(cardCodeList.get(i).getEndTime()));
			}else {
				small.add("~");
				small.add("~");
			}
			small.add(cardCodeList.get(i).getCardCode());
			small.add(cardCodeList.get(i).getCardCodePassword());
            if(cardCodeList.get(i).getStatus().equalsIgnoreCase(CardStatus.INIT.toString())){
                small.add("未使用");
            } if(cardCodeList.get(i).getStatus().equalsIgnoreCase(CardStatus.USED.toString())){
                small.add("已使用");
            }
            if(cardCodeList.get(i).getStatus().equalsIgnoreCase(CardStatus.OVERDUE.toString())){
                small.add("已过期");
            }
            if(cardCodeList.get(i).getStatus().equalsIgnoreCase(CardStatus.CLOSE.toString())){
                small.add("已关闭");
            }
            small.add(cardCodeList.get(i).getRequestId());
            small.add(cardCodeList.get(i).getEmail());
			small.add(format.format(cardCodeList.get(i).getCreateTime()));
			small.add(cardCodeList.get(i).getCreateUser());

			list.add(small);
		}
		return list;
	}


    /**
     * 页面学员卡添加
     */
    @RequestMapping("/card/tocreatUserecard")
    public String toCreateUserCard(HttpServletRequest request) {
        return toCreateUserCard;
    }

    /**
     * 创建学员卡
     *
     * @return
     */
    @RequestMapping("/card/createUsercard")
    @ResponseBody
    public Map<String, Object> createCourseUserCard(HttpServletRequest request, @ModelAttribute("card") Card card) {
        try {
            String courseIds = request.getParameter("courseIds");
            System.out.println(courseIds);
            JsonObject user =  SingletonLoginUtils.getSysUser(request);
            card.setCreateTime(new Date());
            card.setCreateUser(user.get("userName").toString());
            // 创建学员卡操作
            cardService.addCourseUserCard(card, courseIds);
            this.setJson(true, "", null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("createCourseUserCard" + e);
            this.setJson(false, "", null);
        }
        return json;
    }
}
