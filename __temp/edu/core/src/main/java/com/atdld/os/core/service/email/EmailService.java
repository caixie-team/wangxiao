package com.atdld.os.core.service.email;

/**
 * 
 * @ClassName EmailService
 * @package com.fairyhawk.common.service
 * @description
 * @author
 * @Create Date: 2013-3-14 下午7:18:15
 * 
 */

public interface EmailService {
    /**
     * 单人邮件
     * 
     * @param mailto
     * @param fromEmail
     * @param text
     * @param title
     * @throws Exception
     */
    public void sendMail(String mailto, String text, String title) throws Exception;

    /**
     * 群发邮件
     * 
     * @param mailto
     * @param fromEmail
     * @param text
     * @param title
     * @throws Exception
     */
    public void sendBatchMail(String[] mailto, String text, String title);

    /**
     * 单人发送文件
     * 
     * @param mailto
     * @param fromEmail
     * @param text
     * @param title
     * @param filePath
     */
    public void sendMailWithFile(String mailto, String text, String title,
            String[] filePath) throws Exception;

    /**
     * 群发文件
     * 
     * @param mailto
     * @param fromEmail
     * @param text
     * @param title
     * @throws Exception
     */
    public void sendBatchMailWithFile(String[] mailto, String text, String title,
            String[] filePath) throws Exception;

}
