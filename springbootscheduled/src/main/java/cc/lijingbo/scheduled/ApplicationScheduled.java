package cc.lijingbo.scheduled;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("cc.lijingbo.scheduled")
public class ApplicationScheduled {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationScheduled.class);
    }
}
