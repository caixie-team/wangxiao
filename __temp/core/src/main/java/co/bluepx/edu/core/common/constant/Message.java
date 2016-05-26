package co.bluepx.edu.core.common.constant;

public abstract class Message {

	public static final String SEPARATOR = "|";
	public static final String MESSAGE = "message";
	public static final String RESULT = "result";
	public static final String EXIST_USERNAME = "用户名不能重复！";
	public static final String ADD_USER_SUCCESS = "新建用户成功！";
	public static final String LOCK_USER_SUCCESS = "冻结用户成功！";
	public static final String UNLOCK_USER_SUCCESS = "解冻用户成功！";
	public static final String DELETE_USER_SUCCESS = "删除用户成功！";
	public static final String UPDATE_USER_SUCCESS = "更新用户成功！";
	public static final String GRANT_USER_SUCCESS = "授权用户成功！";
	public static final String CHANGE_PASSWORD_SUCCESS = "修改密码成功！";
	public static final String CHANGE_PERSONAL_PASSWORD_SUCCESS = "修改密码成功，请用新密码重新登录！";
	public static final String CHANGE_PASSWORD_FAILED = "原密码不正确，修改密码失败！";
	public static final String PASSWORD_CHANCE_FAILED_RETRY = "修改密码失败！请重试！";
	public static final String PASSWORD_RESET_SUCCESS_RELOGIN = "重置密码成功！请重新登录";
	public static final String VERIFICATION_CODE_NOT_MATCH = "验证码错误，请重新输入！";
	public static final String VERIFICATION_CODE_EXPIRED = "验证码失效，请重新获取！";
	public static final String LOGIN_FAILURE_MESSAGE_PASSWORD = "用户名或密码不正确！";
	public static final String LOGIN_FAILURE_MESSAGE_LOCKED = "该用户被冻结！";
	public static final String LOGIN_FAILURE_MESSAGE_SESSION_AUTH = "此账号已在其他地方登陆！";
	public static final String LOGIN_FAILURE_MESSAGE_ERROR = "服务器异常！";
	public static final String LOGIN_FAILURE_MESSAGE_USER_NOT_FOUND = "该用户不存在！";
	public static final String LOGIN_FAILURE_MESSAGE_NEED_IDENTIFY_CODE = "登陆过于频繁，请输入验证码！";
	public static final String REGISTER_SELECT_EXAM_EXCESSIVE = "最多只能选择两门关注的考试哦！！";
	/**
	 * 公告标题不能为空
	 */
	public static final String ANNOUNCEMENT_TITLE_NULL = "公告标题不能为空！";
	/**
	 * 公告标题不能超过100个字
	 */
	public static final String ANNOUNCEMENT_TITLE = "公告标题不能超过100个字！";
	/**
	 * 公告内容不能为空
	 */
	public static final String ANNOUNCEMENT_REMART_NULL = "公告标题不能超过100个字！";
	/**
	 * 点击数只能是10位以内数字
	 */
	public static final String ANNOUNCEMENT_CLICKNUM = "点击数只能是10位以内数字!";
	/**
	 * 部门名称不能为空!
	 */
	public static final String DEPARTMENT_NAME_NULL = "部门名称不能为空!";
	/**
	 * 部门名称不能多于五十个字!
	 */
	public static final String DEPARTMENT_NAME_SIZE = "部门名称不能多于五十个字!";
	/**
	 * 敏感词名称不能为空!
	 */
	public static final String SENSITIVE_NAME_NULL = "敏感词名称不能为空!";

	/**
	 * 敏感词名称不能超过1000!
	 */
	public static final String SENSITIVE_NAME_SIZE = "敏感词名称不能超过1000!";

	/**
	 * 短息实体验证标题大小提示文字
	 */
	public static final String MESSAGE_TITLE_FIELD_VALIDATE_SIZE = "短信标题长度必须少于100字符！";
	/**
	 * 短息实体验证内容为空提示文字
	 */
	public static final String MESSAGE_CONTENT_FIELD_VALIDATE_NOTEMPTY = "短信正文不能为空!";
	/**
	 * 短息实体验证内容大小提示文字
	 */
	public static final String MESSAGE_CONTENT_FIELD_VALIDATE_SIZE = "短信正文长度必须少于300字符！";

}
