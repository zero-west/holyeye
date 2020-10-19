package me.zw.step04.web;

import lombok.RequiredArgsConstructor;
import me.zw.step04.common.ProxyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
public class LogProxyController {

    private final LogDemoService logDemoService;
    private final ProxyLogger proxyLogger;

    @RequestMapping("log-proxy")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();

        System.out.println("proxyLogger.getClass() = " + proxyLogger.getClass());
        proxyLogger.setRequestURL(requestURL);

        proxyLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
