package cc.lijingbo.springbootmail.service.iml;


import cc.lijingbo.springbootmail.domain.MailBean;
import cc.lijingbo.springbootmail.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;


@Service
public class MailServiceImpl implements IMailService {


    @Value("${lance.mail.sender}")
    private String mailSender;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void sendTextMail(MailBean mailBean) {
        List<String> recipient = mailBean.getRecipient();
        List<String> mailBeanCc = mailBean.getCc();
        String[] to = recipient.toArray(new String[recipient.size()]);
        String[] cc = mailBeanCc.toArray(new String[mailBeanCc.size()]);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mailSender);
        simpleMailMessage.setCc(cc);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("Springboot mail test");
        simpleMailMessage.setText("Springboot test mail content");
        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendMultiMail(MailBean mailBean) {

    }


    @Override
    public void sendHtmlMail(MailBean mailBean) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = assembleHtmlMail(mimeMessage, mailBean);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void sendAttathMentMail(MailBean mailBean) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = assembleHtmlMail(mimeMessage, mailBean);
            addAttachment(helper, ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static" + File.separator + "user.html");
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 组装参数
     */
    private MimeMessageHelper assembleHtmlMail(MimeMessage mimeMessage, MailBean mailBean) throws MessagingException {
        List<String> recipient = mailBean.getRecipient();
        List<String> mailBeanCc = mailBean.getCc();
        String[] to = recipient.toArray(new String[recipient.size()]);
        String[] cc = mailBeanCc.toArray(new String[mailBeanCc.size()]);
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(mailSender);
        helper.setTo(to);
        helper.setCc(cc);
        helper.setSubject(mailBean.getSubject());
        helper.setText(getHtmlContent(mailBean.getContent()), true);
        return helper;
    }


    private String getHtmlContent(String content) {
        Context context = new Context();
        context.setVariable("id", content);
        return templateEngine.process("index", context);
    }

    private void addAttachment(MimeMessageHelper helper, String filepath) throws MessagingException {
        FileSystemResource fileSystemResource = new FileSystemResource(new File(filepath));
        String fileName = filepath.substring(filepath.lastIndexOf(File.separator));
        helper.addAttachment(fileName, fileSystemResource);
    }

}
