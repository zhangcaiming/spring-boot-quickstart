package com.scloud.service;

import com.scloud.CommonTest;
import com.scloud.email.MailService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;



public class MailServiceTest extends CommonTest{

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    private static final String revicer = "zhangcm0597@163.com";

    @Test
    public void testSimpleMail() throws Exception {
        mailService.sendSimpleMail(revicer, "这是一封简单的邮件", "大家好，这是我的第一份邮件");
    }

    @Test
    public void testHtmlMain() {
        String content = "<html>\n" +
                "<body>\n" +
                "   <h3>hello word! 这是一封html邮件！</h3>\n" +
                "</body>\n"+
                "</html>";
        mailService.sendHtmlMail(revicer, "这是一封html邮件", content);

    }

    @Test
    public void testAttachmentMain() {
        String filePath = "D:\\tool\\Xftp_5.0.1028.exe";
        mailService.sendAttachmentsMail(revicer, "主题：带附件的邮件", "有附件，请查收！", filePath);
    }

    @Test
    public void testInlineReesourceMain(){
        String rscId = "andy003";
        String content = "<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\'></body></html>";
        String imgPath = "D:\\temp\\2.jpg";

        mailService.sendInlineResourceMail(revicer, "主题：这是有图片的邮件", content, imgPath, rscId);
    }

    @Test
    public void testTemplateMain() {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "003");
        String emailContent = templateEngine.process("emailTemplate", context);
        mailService.sendHtmlMail(revicer,"主题：这是模板邮件",emailContent);
    }










}
