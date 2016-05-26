package com.atdld.os.edu.service.impl.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.atdld.os.common.exception.AccountException;
import com.atdld.os.common.exception.StaleObjectStateException;
import com.atdld.os.common.service.WebHessianService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.core.util.EnumUtil;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.edu.constants.enums.PayType;
import com.atdld.os.edu.constants.enums.TrxOrderStatus;
import com.atdld.os.edu.constants.web.OrderConstans;
import com.atdld.os.edu.entity.order.TrxReqData;
import com.atdld.os.edu.entity.order.Trxorder;
import com.atdld.os.edu.entity.user.UserAccount;
import com.atdld.os.edu.service.order.TrxHessianService;
import com.atdld.os.edu.service.order.TrxorderService;
import com.atdld.os.edu.service.user.UserAccountService;

/**
 * @ClassName com.atdld.os.edu.service.impl.order.TrxHessianServiceImple
 * @description
 * @author :
 * @Create Date : 2014-9-19 上午10:53:19
 */
@Service("trxHessianService")
public class TrxHessianServiceImple implements TrxHessianService {

    private Logger logger = LoggerFactory.getLogger(TrxHessianServiceImple.class);
    @Autowired
    private TrxorderService trxorderService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private WebHessianService webHessianService;
    /**
     * 订单支付成功回调操作, 保证事务的一致性！！重要
     * 
     * @param sourceMap
     * @return
     */
    public Map<String, String> noTrxTrxOrderComplete(Map<String, String> sourceMap) {
        Map<String, String> res = new HashMap<String, String>();
        try {
            logger.info("noTrxTrxOrderComplete param:"+sourceMap);
            Date date = new Date();
            String total_fee = sourceMap.get("total_fee");//交易金额,外界充值的金额，可能为0
            String requestId = sourceMap.get("requestId");//请求订单号，系统内的
            String payType=sourceMap.get("payType");
            String out_trade_no=sourceMap.get("out_trade_no");//提交到支付宝的请求号
            //查询订单
            Trxorder trxorder = trxorderService.getTrxorderByRequestId(requestId);
            TrxReqData trxReqData = new TrxReqData();
            trxReqData.setBankAmount(new BigDecimal(total_fee));
            trxReqData.setRequestId(requestId);
            trxReqData.setCreateTime(date);
            trxReqData.setPayType(EnumUtil.transStringToEnum(PayType.class, payType));
            trxReqData.setUserId(trxorder.getUserId());
            trxReqData.setOut_trade_no(out_trade_no);
            trxReqData.setTrxorderId(trxorder.getId());
            // 先充值，事务1
            try{
                if(trxReqData.getBankAmount().doubleValue()>0){
                    UserAccount userAccount =  userAccountService.getUserAccountByUserId(trxReqData.getUserId());
                    Map<String,String> accountMap=new HashMap<String, String>();
                    accountMap.put("id", userAccount.getId()+"");
                    accountMap.put("userId", userAccount.getUserId()+"");
                    accountMap.put("balance", userAccount.getBalance().toString());// 账户余额
                    accountMap.put("forzenAmount", userAccount.getForzenAmount().toString());// 冻结金额
                    accountMap.put("cashAmount", userAccount.getCashAmount().toString());// 银行入账
                    accountMap.put("vmAmount", userAccount.getVmAmount().toString());// 课程卡入账
                    accountMap.put("accountStatus", userAccount.getAccountStatus());// 账户状态
                    accountMap.put("version", userAccount.getVersion()+"");// 课程卡入账
                    accountMap.put("trxAmount", trxReqData.getBankAmount().toString());
                    accountMap.put("userId", trxReqData.getUserId()+"");
                    accountMap.put("trxOrderId", trxReqData.getTrxorderId()+"");
                    accountMap.put("requestId", trxReqData.getRequestId());
                    accountMap.put("out_trade_no", trxReqData.getOut_trade_no());
                    accountMap.put("description", "");
                    webHessianService.mobilecredit(accountMap);
                    res.put(OrderConstans.BANKCODE, OrderConstans.SUCCESS);//充值成功就给银行返回成功信息
                }
            }catch ( AccountException e1){
                e1.printStackTrace();
            }
            catch (  StaleObjectStateException e2){
                e2.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
            if (ObjectUtils.isNotNull(trxorder)) {
                if(TrxOrderStatus.SUCCESS.toString().equals( trxorder.getTrxStatus())){
                    res.put(OrderConstans.RESCODE, OrderConstans.SUCCESS);
                    res.put(OrderConstans.RESMSG, "订单已经支付成功");
                    return res;
                }
                if(!TrxOrderStatus.INIT.toString().equals( trxorder.getTrxStatus())){
                    res.put(OrderConstans.RESCODE, OrderConstans.SUCCESS);
                    res.put(OrderConstans.RESMSG, "订单状态异常");
                    return res;
                }
                trxReqData.setAmount(trxorder.getAmount());
                trxReqData.setTrxorderId(trxorder.getId());
                //订单的正常操作，修改订单状态为成功+扣除账户余额
                try {
                    //事务2,账户去支付订单
                    Map<String, String> resOrder= trxorderService.updateCompleteOrder(trxReqData);
                    res.put(OrderConstans.RESCODE,resOrder.get(OrderConstans.RESCODE));
        			res.put("amount", resOrder.get("amount"));
        			res.put("balance", resOrder.get("balance"));
        			res.put("bankAmount", resOrder.get("bankAmount"));
        			res.put("requestId", resOrder.get("requestId"));
                    if(resOrder.get(OrderConstans.RESCODE).equals(OrderConstans.SUCCESS)){
                        res.put(OrderConstans.RESMSG, "订单支付成功！");
                    }else if (resOrder.get(OrderConstans.RESCODE).equals("balance")){//余额不足。
                        if(trxReqData.getBankAmount().doubleValue()>0){
                            res.put(OrderConstans.RESMSG, "订单支付失败，本次交易金额已经充值到您的账户中，请注意查看，请稍后再试");
                        }else{
                            res.put(OrderConstans.RESMSG, "订单支付失败，请稍后再试");
                        }
                    }else{//优惠券编码异常。
                    	if(trxReqData.getBankAmount().doubleValue()>0){
                            res.put(OrderConstans.RESMSG, "订单支付失败，"+res.get(OrderConstans.RESCODE)+"，本次交易金额已经充值到您的账户中，请注意查看");
                        }else{
                            res.put(OrderConstans.RESMSG, "订单支付失败，"+res.get(OrderConstans.RESCODE)+"！");
                        }
                    }
                } catch (Exception e) {
                    logger.error("noTrxTrxOrderComplete.trxorderService",e);
                }
            } else {
                res.put(OrderConstans.RESCODE, "ordernull");
                res.put(OrderConstans.RESMSG, "订单信息异常，请稍后再试"); 
                logger.info("noTrxTrxOrderComplete order is null");
            }

            
        } catch (Exception e) {
            res.put(OrderConstans.RESCODE, "error");
            res.put(OrderConstans.RESMSG, "操作异常，请稍后再试！");
            logger.error("noTrxTrxOrderComplete error", e);
            
        }

        return res;
    }
}
