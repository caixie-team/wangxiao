package com.atdld.os.core.service.email;

import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * 
 * @ClassName EmailServiceImpl
 * @package com.fairyhawk.common.service
 * @description 邮件服务实现
 * @author
 * @Create Date: 2013-3-14 下午7:14:41
 * 
 */
@Service("emailService")
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    private static final Log logger = LogFactory.getLog(EmailServiceImpl.class);

    /**
     * 单人邮件
     */
    @Override
    public void sendMail(String mailto, String text, String title) throws Exception {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true,
                "UTF-8");
        messageHelper.setFrom(new InternetAddress(javaMailSender.getUsername()));
        messageHelper.setSubject(title);
        messageHelper.setText(text, true);
        messageHelper.setTo(new InternetAddress(mailto));
        mimeMessage = messageHelper.getMimeMessage();
        // javaMailSender.send(mimeMessage);
        EmailThread et = new EmailThread(mimeMessage);
        et.start();

    }

    /**
     * 群发邮件
     */
    @Override
    public void sendBatchMail(String[] mailto, String text, String title) {
        for (int i = 0; i < mailto.length; i++) {
            try {
                sendMail(mailto[i], text, title);
            } catch (Exception e) {
                logger.error("+++ sendBatchMail error email:" + mailto[i]);
            }
        }
    }

    /**
     * 单人文件
     */
    public void sendMailWithFile(String mailto, String text, String title,
            String[] filePath) throws Exception {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true,
                "UTF-8");
        messageHelper.setFrom(new InternetAddress(javaMailSender.getUsername()));
        messageHelper.setSubject(title);
        messageHelper.setText(text, true);
        messageHelper.setTo(new InternetAddress(mailto));
        mimeMessage = messageHelper.getMimeMessage();

        if (filePath != null) {
            BodyPart mdp = new MimeBodyPart();// 新建一个存放信件内容的BodyPart对象
            mdp.setContent(text, "text/html;charset=UTF-8");// 给BodyPart对象设置内容和格式/编码方式
            Multipart mm = new MimeMultipart();// 新建一个MimeMultipart对象用来存放BodyPart对象
            mm.addBodyPart(mdp);// 将BodyPart加入到MimeMultipart对象中(可以加入多个BodyPart)
            // 把mm作为消息对象的内容
            MimeBodyPart filePart;
            FileDataSource filedatasource;
            // 逐个加入附件
            for (int j = 0; j < filePath.length; j++) {
                filePart = new MimeBodyPart();
                filedatasource = new FileDataSource(filePath[j]);
                filePart.setDataHandler(new DataHandler(filedatasource));
                try {
                    filePart.setFileName(MimeUtility.encodeText(filedatasource.getName()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mm.addBodyPart(filePart);
            }
            mimeMessage.setContent(mm);
        }
        EmailThread et = new EmailThread(mimeMessage);
        et.start();
    }

    /**
     * 群发文件
     */
    @Override
    public void sendBatchMailWithFile(String[] mailto, String text, String title,
            String[] filePath) throws Exception {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true,
                "UTF-8");
        messageHelper.setFrom(new InternetAddress(MimeUtility.encodeText(javaMailSender
                .getUsername())));
        messageHelper.setSubject(title);
        if (filePath != null) {
            BodyPart mdp = new MimeBodyPart();// 新建一个存放信件内容的BodyPart对象
            mdp.setContent(text, "text/html;charset=UTF-8");// 给BodyPart对象设置内容和格式/编码方式
            Multipart mm = new MimeMultipart();// 新建一个MimeMultipart对象用来存放BodyPart对象
            mm.addBodyPart(mdp);// 将BodyPart加入到MimeMultipart对象中(可以加入多个BodyPart)
            // 把mm作为消息对象的内容
            MimeBodyPart filePart;
            FileDataSource filedatasource;
            // 逐个加入附件
            for (int j = 0; j < filePath.length; j++) {
                filePart = new MimeBodyPart();
                filedatasource = new FileDataSource(filePath[j]);
                filePart.setDataHandler(new DataHandler(filedatasource));
                try {
                    filePart.setFileName(MimeUtility.encodeText(filedatasource.getName()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mm.addBodyPart(filePart);
            }
            mimeMessage.setContent(mm);
        } else {
            messageHelper.setText(text, true);
        }

        List<InternetAddress> list = new ArrayList<InternetAddress>();// 不能使用string类型的类型，这样只能发送一个收件人
        for (int i = 0; i < mailto.length; i++) {
            list.add(new InternetAddress(mailto[i]));
        }
        InternetAddress[] address = list.toArray(new InternetAddress[list.size()]);

        mimeMessage.setRecipients(Message.RecipientType.TO, address);
        mimeMessage = messageHelper.getMimeMessage();

        EmailThread et = new EmailThread(mimeMessage);
        et.start();

    }

    /**
     * 内部类发送邮件线程
     * 
     * @ClassName EmailThread
     * @package com.fairyhawk.common.service
     * @description
     * @author
     * @Create Date: 2013-3-14 下午7:27:12
     * 
     */
    class EmailThread extends Thread {

        private final MimeMessage mimeMessage;

        public EmailThread(MimeMessage mimeMessage) {
            this.mimeMessage = mimeMessage;
        }

        @Override
        public void run() {
            javaMailSender.send(mimeMessage);
        }

    }

}
