package cc.lijingbo.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.lijingbo.mail.domain.MailBean;
import cc.lijingbo.mail.service.IMailService;


@RestController
@RequestMapping("/mailservice")
public class MailController {

    @Autowired
    private IMailService mailService;


    @RequestMapping("/sendSimpleMail")
    public void sendSimpleMail(){
        mailService.sendTextMail(new MailBean());
    }

    @RequestMapping("/sendHtmlMail")
    public void sendHtmlMail(){
        MailBean mailBean = new MailBean();
        mailBean.setSubject("Springboot Html mail test");
        mailBean.setContent("Springboot html content mail test");
        mailBean.addCc("xxxx");
        mailBean.addRecipient("xxxx");
        mailService.sendHtmlMail(mailBean);
    }

    @RequestMapping("/sendAttathmengMail")
    public void sendAttathmentMail(){
        MailBean mailBean = new MailBean();
        mailBean.setSubject("Springboot Attathment mail test");
        mailBean.setContent("Springboot Attathment content mail test");
        mailBean.addCc("xxxx");
        mailBean.addRecipient("xxxx");
        mailService.sendAttathMentMail(mailBean);
    }
}
