package cc.lijingbo.springboot.config;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

    /**
     * 自定义错误返回的数据
     * {"success":false,"err_code":"x","err_msg":"","result_data":{}}
     */
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        // 添加自定义的错误字段
        errorAttributes.put("success", false);
        errorAttributes.put("err_code", "-1");
        errorAttributes.put("err_msg", errorAttributes.get("message"));
        errorAttributes.put("result_data", null);
        // 移除已有的错误字段
        errorAttributes.remove("error");
        errorAttributes.remove("timestamp");
        errorAttributes.remove("status");
        errorAttributes.remove("message");
        errorAttributes.remove("path");
        return errorAttributes;
    }
}
