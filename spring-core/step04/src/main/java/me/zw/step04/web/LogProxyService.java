package me.zw.step04.web;

import lombok.RequiredArgsConstructor;
import me.zw.step04.common.ProxyLogger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogProxyService {

    private final ProxyLogger proxyLogger;

    public void logic(String id) {
        proxyLogger.log("service id = " + id);
    }
}
