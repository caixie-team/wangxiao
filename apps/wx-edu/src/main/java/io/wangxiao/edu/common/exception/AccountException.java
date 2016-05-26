package io.wangxiao.edu.common.exception;

import io.wangxiao.commons.exception.BaseException;

public class AccountException extends BaseException {

    /**
     * 账户部分异常：10打头
     */
    public static final int ACCOUNT_STATUS_INVALID = 1001;// 账户状态无效

    public static final int ACCOUNT_NOT_FOUND = 1002;// 帐户未发现

    public static final int AMOUNT_INVALID = 1003;// 金额无效

    public static final int ACCOUNT_NOT_ENOUGH = 1004; // 账户余额不足

    public static final int USER_NOT_FOUND = 1005; // 用户不存在

    public AccountException() {
        super();
    }

    public AccountException(int code) {
        super(code);
    }

    public AccountException(String errMsg) {
        super(errMsg);
    }
}
