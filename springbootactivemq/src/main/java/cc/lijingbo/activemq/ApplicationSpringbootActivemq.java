package cc.lijingbo.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;

@SpringBootApplication
@RestController
public class ApplicationSpringbootActivemq {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationSpringbootActivemq.class, args);
    }

    @Bean
    public Queue queue(){
        return new ActiveMQQueue("active.queue");
    }


    @GetMapping("/hello")
    public String hello() {
        return "Hello,Redis";
    }
}
