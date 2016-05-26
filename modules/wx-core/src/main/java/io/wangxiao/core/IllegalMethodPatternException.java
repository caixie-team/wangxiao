package io.wangxiao.core;
/**
 * 方法名和规范不一致异常。
 * 如 dao中必须以 add, find, delete, update开头，如果不符合规范，可以扔出此异常。
 *
 */
public class IllegalMethodPatternException extends RuntimeException {
	
	public IllegalMethodPatternException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7064423373278443628L;


}
