package cc.lijingbo.springbootmail.controller;


import cc.lijingbo.springbootmail.domain.MailBean;
import cc.lijingbo.springbootmail.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
        mailBean.addCc("xxx@xxx.com");
        mailBean.addRecipient("xxx@xxx.com");
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
