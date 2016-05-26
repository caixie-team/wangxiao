package io.wangxiao.commons.exception;

/**
 * @ClassName BaseException
 */
public class BaseException extends Exception {

    private static final long serialVersionUID = 8562170304258482768L;

    public int code;// 错误码

    public BaseException() {
        super();
    }

    public BaseException(int code) {
        this.code = code;
    }

    public BaseException(String errorMsg) {
        super(errorMsg);
    }

    @Override
    public void printStackTrace() {
        System.out.println("error code:"+code);
        super.printStackTrace();
    }

    public static String getErrorMessage(Exception e) {
        StackTraceElement ste = e.getStackTrace()[0];
        String className = ste.getClassName();
        if (className.indexOf(".") > -1) {
            className = className.substring(className.lastIndexOf(".") + 1, className.length());
        }
        String methodName = ste.getMethodName();
        int line = ste.getLineNumber();
        String exMsg = "类名：" + className + ",方法:" + methodName + ",行数:" + line + ",异常：" + e.toString();
        return exMsg;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
