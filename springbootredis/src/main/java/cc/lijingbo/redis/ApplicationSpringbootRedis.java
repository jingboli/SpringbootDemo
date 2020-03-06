package cc.lijingbo.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableCaching
@RestController
public class ApplicationSpringbootRedis {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationSpringbootRedis.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello,Redis";
    }
}
