package io.wangxiao.commons.service.email;

/**
 * @ClassName EmailService
 */

public interface EmailService {
    /**
     * 单人邮件
     *
     * @param mailto
     * @param text
     * @param title
     * @throws Exception
     */
    void sendMail(String mailto, String text, String title) throws Exception;

    /**
     * 群发邮件
     *
     * @param mailto
     * @param text
     * @param title
     * @throws Exception
     */
    void sendBatchMail(String[] mailto, String text, String title);

    /**
     * 单人发送文件
     *
     * @param mailto
     * @param text
     * @param title
     * @param filePath
     */
    void sendMailWithFile(String mailto, String text, String title,
                          String[] filePath) throws Exception;

    /**
     * 群发文件
     *
     * @param mailto
     * @param text
     * @param title
     * @throws Exception
     */
    void sendBatchMailWithFile(String[] mailto, String text, String title,
                               String[] filePath) throws Exception;

}
