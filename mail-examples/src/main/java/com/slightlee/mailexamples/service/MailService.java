package com.slightlee.mailexamples.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @Description:
 * @Auther: LIMING
 * @Email: lmm_work@163.com
 * @Date: 2019/4/11/11 13:06
 */
@Service
public class MailService {

    private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    JavaMailSender mailSender;


    public void sayHello() {
        System.out.println("hello world");
    }

    /**
     * 文本邮件
     * SimpleMailMessage
     * JavaMailSender
     *
     * @param to      发送给谁
     * @param subject 标题
     * @param content 内容
     */
    public void sendSimpleMail(String to, String subject, String content) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        message.setFrom(from);

        mailSender.send(message);
    }

    /**
     * HTML 邮件
     * MimeMessage
     * MimeMessageHelper
     *
     * @param to      发送给谁
     * @param subject 标题
     * @param content 内容
     */
    public void sendHtmlMail(String to, String subject, String content) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        helper.setFrom(from);

        mailSender.send(message);
    }

    /**
     * 附件邮件
     * FileSystemResource
     *
     * @param to      发送给谁
     * @param subject 标题
     * @param content 内容
     * @param filPath 文件地址
     */
    public void sendAttachFileMail(String to, String subject, String content, String filPath) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        helper.setFrom(from);

        FileSystemResource file = new FileSystemResource(new File(filPath));
        String fileName = file.getFilename();
        helper.addAttachment(fileName, file);
        // 如果多个文件 可以 把 filPath  设置为一个数组，然后遍历

        mailSender.send(message);

    }

    /**
     *  图片 邮件
     *
     * @param to      发送给谁
     * @param subject 标题
     * @param content 内容
     * @param rscPath 图片路径
     * @param rscId   图片id
     * @throws MessagingException
     */
    public void sendImagerMail(String to, String subject, String content,String rscPath,String rscId){

        logger.info("发送邮件开始 ---------------------------");

        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            helper.setFrom(from);

            FileSystemResource file = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId,file);
            logger.info("发送成功");
        } catch (MessagingException e) {
           logger.error("发送失败",e);
        }

        mailSender.send(message);
    }


}