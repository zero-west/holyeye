package me.zw.advanced.trace

import me.zw.advanced.trace.callback.TraceTemplate
import me.zw.advanced.trace.logtrace.LogTrace
import me.zw.advanced.trace.logtrace.ThreadLocalLogTrace
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LogTraceConfig {

    @Bean
    fun logTrace(): LogTrace {
        return ThreadLocalLogTrace()
    }

    @Bean
    fun traceTemplate(
        @Autowired logTrace: LogTrace,
    ): TraceTemplate {
        return TraceTemplate(logTrace)
    }
}