package cc.lijingbo.js.demo;


import cc.lijingbo.js.demo.domain.ResponseWrapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/", produces = "application/json;charset=utf-8")
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseWrapper excel(String username, String password) {
        ResponseWrapper responseWrapper = new ResponseWrapper();
        try {
            System.out.println("usename: " + username + " ,password: " + password);
            if ("bill".equals(username)&&"123".equals(password)){
                responseWrapper.setCode(0);
                responseWrapper.setMessage("登录成功");
            }else {
                responseWrapper.setCode(-1);
                responseWrapper.setMessage("登录失败");
                responseWrapper.setData("账号或者密码错误");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            responseWrapper.setCode(-1);
            responseWrapper.setMessage("登录失败");
            responseWrapper.setData(ex.getMessage());
        }
        return responseWrapper;
    }

}
