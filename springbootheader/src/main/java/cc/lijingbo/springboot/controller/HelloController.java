package cc.lijingbo.springboot.controller;

import cc.lijingbo.springboot.annotation.RequestBodyAndHeader;
import cc.lijingbo.springboot.domain.BodyAndHeaderParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController()
@RequestMapping("header")
public class HelloController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/printname/{name}", method = RequestMethod.GET)
    public String getHeader1(@PathVariable String name) {
        String token = httpServletRequest.getHeader("token");
        logger.debug("header:" + token + ", name:" + name);
        return "header-> token:" + token + ", name:" + name;
    }


    @GetMapping(value = "/printname2/{name}")
    public String getHeader2(@PathVariable String name, @RequestHeader(value = "token") String token) {
        logger.debug("header:" + token + ", name:" + name);
        return "header-> token:" + token + ", name:" + name;
    }


    @GetMapping(value = "/printname3/{name}")
    public String getHeader3(@PathVariable String name, @RequestHeader HttpHeaders headers) {
        logger.debug("header:" + headers.getFirst("token") + ", name:" + name);
        logger.debug("header:" + headers.get("token").get(0) + ", name:" + name);
        return "header-> token:" + headers.getFirst("token") + ", name:" + name;
    }

    @PostMapping(value = "/bodyandheader")
    public String headerAndBody(@RequestBodyAndHeader BodyAndHeaderParams params) {
        return "header-> token:" + params.getHeadertoken() + ", device:" + params.getHeaderdevice() + ", body-> name:" + params.getBodyName() + ", sex:" + params.getBodySex();
    }
}
