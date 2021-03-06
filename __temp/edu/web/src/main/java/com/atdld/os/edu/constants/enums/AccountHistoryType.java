package com.atdld.os.edu.constants.enums;

/**
 * @ClassName com.atdld.os.edu.constants.enums.AccountHistoryType
 * @description 账户历史类型
 * @author :
 * @Create Date : 2014-9-19 下午6:00:26
 */
public enum AccountHistoryType {

    SALES, // 消费，出账
    REFUND, // 退款
    CASHLOAD,//现金充值
    VMLOAD,//课程卡充值
    ADMINLOAD,//后台充值
    ADMINREFUND;//后台扣款
     
    public boolean isSales() {
        switch (this) {
        case SALES:
            return true;
        default:
            return false;
        }
    }

    public boolean isRefund() {
        switch (this) {
        case REFUND:
            return true;
        default:
            return false;
        }
    }

    public boolean isLoad() {
        switch (this) {
        case CASHLOAD:
            return true;
        case VMLOAD:
            return true;
        default:
            return false;
        }
    }

}
