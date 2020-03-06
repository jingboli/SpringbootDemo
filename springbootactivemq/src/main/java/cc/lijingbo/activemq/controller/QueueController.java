package cc.lijingbo.activemq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;


@RestController
public class QueueController {
    @Autowired
    private Queue queue;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @RequestMapping("/send")
    public void send() {
        String message = "新发送的第 %s 条消息!";
        for (int i = 0; i < 10; i++) {
            jmsMessagingTemplate.convertAndSend(queue, String.format(message, i));
        }
    }

    @JmsListener(destination = "active.queue")
    public void readActiveQueue(String message) {
        System.out.println("接收到消息：" + message);
    }

}
