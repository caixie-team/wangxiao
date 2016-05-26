package io.wangxiao.edu.common.exception;


import io.wangxiao.commons.exception.BaseException;

/**
 * @description 事务，乐观锁异常
 */
public class StaleObjectStateException extends BaseException {
    /**
     * 乐观锁异常
     */
    public static final int OPTIMISTIC_LOCK_ERROR = 9001; // 乐观锁异常
    public static final int OPTIMISTIC_UPDATE_NONE = 9002; // 更新数据响应行数为0
    public static final int OPTIMISTIC_OUTTRADENO_EXIT = 9003; // 账户历史已存在该支付宝订单号


    public StaleObjectStateException() {

        super();
    }

    public StaleObjectStateException(int code) {

        super(code);
    }

    public StaleObjectStateException(String errorMsg) {

        super(errorMsg);
    }
}
