package co.bluepx.edu.core.enums;

/**
 * @description 订单明细的状态（是否可以观看用此字段）
 */
public enum AuthStatus {
    INIT,//初始化 未开始
    SUCCESS,//支付成功
    REFUND,//退费,
    CLOSED,//关闭，后台手动关闭
    LOSED//已过期，系统定时过期
}
