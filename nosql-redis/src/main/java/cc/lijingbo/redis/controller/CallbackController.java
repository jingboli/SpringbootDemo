package cc.lijingbo.redis.controller;

import cc.lijingbo.redis.domain.UserInfo;
import cc.lijingbo.redis.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallbackController {

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping(value = "/callback")
    public String callBack() {
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("lijingbo@com.cn");
        userInfo.setEmployee_number("987456123");
        userInfo.setUser_id("rf987456123");
        userInfo.setUser_name("Jerome Li");
        String key = userInfo.getEmployee_number() + "____wps";
        boolean result = redisUtils.set(key, userInfo, 30);
        return result ? "存入成功" : "存入失败";
    }

    @GetMapping(value = "/get/info")
    public String getUserInfo() {
        Object o = redisUtils.get("987456123____wps");
        if (o != null) {
            UserInfo info = (UserInfo) o;
            return info.toString();
        }
        return "失败了";
    }
}
