package cc.lijingbo.springbootmail.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MailBean implements Serializable {
    private List<String> recipient = new ArrayList<>();
    private String subject;
    private String content;
    private String sender;
    private List<String> cc = new ArrayList<>();

    public List<String> getRecipient() {
        if (recipient == null) {
            return new ArrayList();
        }
        return recipient;
    }

    public void addRecipient(String to) {
        this.recipient.add(to);
    }

    public void setRecipient(List<String> recipient) {
        this.recipient = recipient;
    }

    public String getSender() {
        return sender == null ? "" : sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getCc() {
        if (cc == null) {
            return new ArrayList();
        }
        return cc;
    }

    public void addCc(String cc) {
        this.cc.add(cc);
    }

    public void setCc(List<String> cc) {
        this.cc = cc;
    }

    public String getSubject() {
        return subject == null ? "" : subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
