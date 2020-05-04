package cc.lijingbo.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApplicationSpringbootError {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationSpringbootError.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        int i = 1 / 0;
        return "hello";
    }
}
