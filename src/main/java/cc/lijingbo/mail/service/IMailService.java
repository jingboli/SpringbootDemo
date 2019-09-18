package cc.lijingbo.mail.service;

import cc.lijingbo.mail.domain.MailBean;


public interface IMailService {

    void sendTextMail(MailBean mailBean);

    void sendMultiMail(MailBean mailBean);

    void sendHtmlMail(MailBean mailBean);

    void sendAttathMentMail(MailBean mailBean);
}
