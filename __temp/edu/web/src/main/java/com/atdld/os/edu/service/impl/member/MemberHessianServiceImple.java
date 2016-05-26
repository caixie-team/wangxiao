package com.atdld.os.edu.service.impl.member;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.common.exception.AccountException;
import com.atdld.os.common.exception.StaleObjectStateException;
import com.atdld.os.core.util.EnumUtil;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.edu.constants.enums.AccountBizType;
import com.atdld.os.edu.constants.enums.AccountHistoryType;
import com.atdld.os.edu.constants.enums.AccountType;
import com.atdld.os.edu.constants.enums.PayType;
import com.atdld.os.edu.constants.enums.TrxOrderStatus;
import com.atdld.os.edu.constants.web.OrderConstans;
import com.atdld.os.edu.entity.member.MemberOrder;
import com.atdld.os.edu.entity.member.MemberOrderReqData;
import com.atdld.os.edu.entity.user.UserAccount;
import com.atdld.os.edu.service.member.MemberHessianService;
import com.atdld.os.edu.service.member.MemberOrderService;
import com.atdld.os.edu.service.user.UserAccountService;

/**
 * @ClassName com.atdld.os.edu.service.impl.member.MemberHessianServiceImple
 * @description
 * @author :
 * @Create Date : 2014-9-19 上午10:53:19
 */
@Service("memberHessianService")
public class MemberHessianServiceImple implements MemberHessianService {

    private Logger logger = LoggerFactory.getLogger(MemberHessianServiceImple.class);
    @Autowired
    private MemberOrderService memberOrderService;
    @Autowired
    private UserAccountService userAccountService;

    /**
     * 订单支付成功回调操作, 保证事务的一致性！！重要
     * 
     * @param sourceMap
     * @return
     */
    public Map<String, String> noMemberOrderComplete(Map<String, String> sourceMap) {
        Map<String, String> res = new HashMap<String, String>();//
        try {
            logger.info("noMemberOrderComplete param:"+sourceMap);
            Date date = new Date();
            String total_fee = sourceMap.get("total_fee");//交易金额,外界充值的金额，可能为0
            String requestId = sourceMap.get("requestId");//请求订单号
            String userId= sourceMap.get("userId");//用户id
            String payType=sourceMap.get("payType");
            String out_trade_no=sourceMap.get("out_trade_no");//提交到支付宝的请求号 
            //查询订单
            MemberOrder memberOrder = memberOrderService.getMemberOrderByRequestId(requestId);
            MemberOrderReqData memberReqData = new MemberOrderReqData();
            memberReqData.setBankAmount(new BigDecimal(total_fee));
            memberReqData.setRequestId(requestId);
            memberReqData.setCreateTime(date);
            memberReqData.setPayType(EnumUtil.transStringToEnum(PayType.class, payType));
            memberReqData.setUserId(Long.valueOf(userId));
            memberReqData.setOut_trade_no(out_trade_no);
            memberReqData.setMemberOrderId(memberOrder.getId());
            try{
            	// 先充值，事务1
	            if(memberReqData.getBankAmount().doubleValue()>0){
	                UserAccount userAccount =  userAccountService.getUserAccountByUserId(memberReqData.getUserId());
	                userAccountService.credit(userAccount, memberReqData.getBankAmount(), AccountType.CASH, AccountHistoryType.CASHLOAD, memberReqData.getUserId(), memberReqData.getMemberOrderId(), memberReqData.getRequestId(),
	                		memberReqData.getOut_trade_no(),  new Date(), "", true, AccountBizType.MEMBER);//会员订单
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
            if (ObjectUtils.isNotNull(memberOrder)) {
                if(TrxOrderStatus.SUCCESS.toString().equals(memberOrder.getTrxStatus())){
                    res.put(OrderConstans.RESCODE, OrderConstans.SUCCESS);
                    res.put(OrderConstans.RESMSG, "订单已经支付成功");
                    return res;
                }
                if(!TrxOrderStatus.INIT.toString().equals( memberOrder.getTrxStatus())){
                    res.put(OrderConstans.RESCODE, OrderConstans.SUCCESS);
                    res.put(OrderConstans.RESMSG, "订单状态异常");
                    return res;
                }
                memberReqData.setAmount(memberOrder.getAmount());
                memberReqData.setMemberOrderId(memberOrder.getId());
              //订单的正常操作，修改订单状态为成功+扣除账户余额
                try {
                    //事务2,账户去支付订单
                	Map<String, String> resOrder= memberOrderService.updateCompleteMemberOrder(memberReqData);
                	res.put(OrderConstans.RESCODE,resOrder.get(OrderConstans.RESCODE));
        			res.put("amount", resOrder.get("amount"));
        			res.put("balance", resOrder.get("balance"));
        			res.put("bankAmount", resOrder.get("bankAmount"));
        			res.put("requestId", resOrder.get("requestId"));
                	if(resOrder.get(OrderConstans.RESCODE).equals(OrderConstans.SUCCESS)){
                        res.put(OrderConstans.RESMSG, "订单支付成功！");
                    }else if (resOrder.get(OrderConstans.RESCODE).equals("balance")){//余额不足。
                        if(memberReqData.getBankAmount().doubleValue()>0){
                            res.put(OrderConstans.RESMSG, "订单支付失败，本次交易金额已经充值到您的账户中，请注意查看，请稍后再试！");
                        }else{
                            res.put(OrderConstans.RESMSG, "订单支付失败，请稍后再试");
                        }
                    }else{//优惠券错误信息。
                        if(memberReqData.getBankAmount().doubleValue()>0){
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
