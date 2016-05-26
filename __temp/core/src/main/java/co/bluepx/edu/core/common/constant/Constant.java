package co.bluepx.edu.core.common.constant;

public final class Constant {
	
	public static final int TRUE = 2;                  //置顶
	public static final int FALSE = 1;                 //正常显示
	
	public static final int BY_DAY = 0;                //按天
	public static final int BY_DATE = 1;               //按日期
	
	/* 操作类型 */
	public static final int OPERATOR_STATUS = 1;       //状态变更的操作   
	public static final int OPERATOR_RESPONSE = 2;     //是否准许回复的操作
	public static final int OPERATOR_TOP = 3;          //是否置顶的操作
	public static final int OPERATOR_VISIBLE = 4;      //可见度的操作
	public static final int OPERATOR_DELETE = 5;       //删除操作

	public static final int STATUS_OK = 1;             //状态(正常)
	public static final int STATUS_DELETE = 0;         //状态(删除)
	public static final int STATUS_FROZEN = 1;         //状态(冻结)

    public static final long ROOT_MODULE_ID = -1;
}
