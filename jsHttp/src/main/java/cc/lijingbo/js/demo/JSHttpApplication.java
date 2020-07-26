package cc.lijingbo.js.demo;

import cc.lijingbo.js.demo.domain.ResponseWrapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JSHttpApplication {

    public static void main(String[] args) {
        SpringApplication.run(JSHttpApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello1")
    public ResponseWrapper hello1() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int a =1/0;
        return ResponseWrapper.succeed("执行成功1", null);
    }

    @GetMapping("/hello2")
    public ResponseWrapper hello2() {
        return ResponseWrapper.succeed("执行成功2", null);
    }

    @GetMapping("/hello3")
    public ResponseWrapper hello3() {
        return ResponseWrapper.succeed("执行成功3", null);
    }

    @GetMapping("/hello4")
    public ResponseWrapper hello4() {
        return ResponseWrapper.succeed("执行成功4", null);
    }

    @GetMapping("/hello5")
    public ResponseWrapper hello5() {
        return ResponseWrapper.succeed("执行成功5", null);
    }

    @GetMapping("/hello6")
    public ResponseWrapper hello6() {
        return ResponseWrapper.succeed("执行成功6", null);
    }

    @GetMapping("/hello7")
    public ResponseWrapper hello7() {
        return ResponseWrapper.succeed("执行成功7", null);
    }

    @GetMapping("/hello8")
    public ResponseWrapper hello8() {
        return ResponseWrapper.succeed("执行成功8", null);
    }
}
