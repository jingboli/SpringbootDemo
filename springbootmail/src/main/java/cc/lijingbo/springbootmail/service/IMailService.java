package cc.lijingbo.springbootmail.service;


import cc.lijingbo.springbootmail.domain.MailBean;

public interface IMailService {

    void sendTextMail(MailBean mailBean);

    void sendMultiMail(MailBean mailBean);

    void sendHtmlMail(MailBean mailBean);

    void sendAttathMentMail(MailBean mailBean);
}
