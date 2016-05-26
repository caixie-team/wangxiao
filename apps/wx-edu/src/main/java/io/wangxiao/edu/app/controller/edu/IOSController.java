package io.wangxiao.edu.app.controller.edu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.wangxiao.commons.util.MD5;
import io.wangxiao.edu.app.entity.website.AppIosCheck;
import io.wangxiao.edu.app.service.website.AppIosCheckService;
import io.wangxiao.edu.common.util.IOSVerify;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.constants.enums.BuyState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Controller
public class IOSController extends EduBaseController {
	private static final Logger logger = LoggerFactory.getLogger(IOSController.class);
	public static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	public static JsonParser jsonParser = new JsonParser();
	@Autowired
	private AppIosCheckService appIosCheckService;

	/**
	 * 客户端向服务器验证
	 * 
	 * 
	 * * checkState A 验证成功有效(返回收据) B 账单有效，但己经验证过 C 服务器数据库中没有此账单(无效账单) D 不处理
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/ios/check")
	@ResponseBody
	public Map<String, Object> IOSVerify(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> json = null;
		try {
			System.out.println(new Date() + "  来自苹果端的验证...");
			// 苹果客户端传上来的收据,是最原据的收据
			String receipt = request.getParameter("receipt");
			if (receipt == null) {
				json = this.getJsonMap(false, "receiptIsNull", null);
				return json;
			}
			System.out.println(receipt);
			// 拿到收据的MD5
			String md5_receipt = MD5.getMD5(receipt);
			// 默认是无效账单
			String result = BuyState.INVALID.toString() + "#" + md5_receipt;
			// 查询数据库，看是否是己经验证过的账号
			boolean isExists = appIosCheckService.isExistsIOSReceipt(md5_receipt);
			String verifyResult = null;
			if (!isExists) {
				String verifyState = IOSVerify.getEnvironment(receipt);
				logger.info("verifyState==========================:" + verifyState);
				verifyResult = IOSVerify.buyAppVerify(receipt, verifyState);
				logger.info("verifyResult==========================:" + verifyResult);
				if (verifyResult == null) {
					// 苹果服务器没有返回验证结果
					result = BuyState.NORESULT.toString() + "#" + md5_receipt;
					json = this.getJsonMap(false, "服务器没有返回验证", result);
				} else {
					// 跟苹果验证有返回结果------------------
					JsonObject job = jsonParser.parse(verifyResult).getAsJsonObject();
					String states = job.get("status").getAsString();
					if (states.equals("0"))// 验证成功
					{
						String r_receipt = job.get("receipt").toString();
						JsonObject returnJson = jsonParser.parse(r_receipt).getAsJsonObject();
						// 产品ID
						String product_id = returnJson.get("product_id").toString();
						// 数量
						String quantity = returnJson.get("quantity").toString();
						// 跟苹果的服务器验证成功
						result = BuyState.SUCCESS.toString() + "#" + md5_receipt + "_" + product_id + "_" + quantity;
						// 交易日期
						String purchase_date = returnJson.get("purchase_date").toString();
						;
						// 保存到数据库
						AppIosCheck appIosCheck = new AppIosCheck();
						appIosCheck.setMd5Receipt(md5_receipt);
						appIosCheck.setProductId(product_id);
						appIosCheck.setPurchaseDate(purchase_date);
						appIosCheck.setRReceipt(r_receipt);
						appIosCheckService.saveIOSReceipt(appIosCheck);
						json = this.getJsonMap(true, "验证成功", result);
					} else {
						// 账单无效
						result = BuyState.INVALID.toString() + "#" + md5_receipt + "#" + states;
						json = this.getJsonMap(false, "账单无效", result);
					}
					// 跟苹果验证有返回结果------------------
				}
				// 传上来的收据有购买信息==end=============
			} else {
				// 账单有效，但己验证过
				result = BuyState.EXIST.toString() + "#" + md5_receipt;
				json = this.getJsonMap(false, "账单以验证", result);
			}

		} catch (Exception e) {
			logger.error("IOSController.IOSVerify", e);
			json = this.getJsonMap(false, "error", null);
		}
		return json;

	}
}
