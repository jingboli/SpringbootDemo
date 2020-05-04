package cc.lijingbo.springboot.controller;

import cc.lijingbo.springboot.utils.Utils;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonFactoryBuilder;
import com.fasterxml.jackson.core.TSFBuilder;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class ClientController {

    @Value("$custom-name")
    private String customName;

    @RequestMapping("/info")
    public String getInfo(HttpServletRequest request) {
        String ipAddress = Utils.getIpAddress(request);

        return "ip :" + ipAddress;
    }
}
