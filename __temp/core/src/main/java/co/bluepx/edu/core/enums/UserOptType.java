package co.bluepx.edu.core.enums;
/**
 * 
 * @description 用户操作
 */
public enum UserOptType {
	SYSFROZEN,//系统用户冻结
	SYSACTIVE,//系统用户正常
	SYSDELETE,//系统用户删除
	ACTIVE,//恢复正常
	DISABLE,//学员禁用
	CHANGEPWD,//修改密码
	GIVECOURSE,//赠送课程
	FROZEN,//冻结
	ADMINLOAD,//后台操作
	AUDIT,//审核
	REFUND,//退费
	DEPAL,//延期
	CLOSED,//关闭课程
	CHANGECOURSE,//换课
}
