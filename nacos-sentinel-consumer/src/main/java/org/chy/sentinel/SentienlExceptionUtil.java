package org.chy.sentinel;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

/**
 * SentienlExceptionUtil 未起到作用
 *
 * Created by chy on 2021/7/19.
 */
public class SentienlExceptionUtil {

    /**
     * 限流熔断业务逻辑
     *
     * 当使用 RestTemplate 调用被 Sentinel 熔断后,发生 BlockException 才会触发
     *
     * @see  com.alibaba.csp.sentinel.slots.block.degrade.DegradeException
     * @see  com.alibaba.cloud.sentinel.custom.SentinelProtectInterceptor#handleBlockException(org.springframework.http.HttpRequest, byte[], org.springframework.http.client.ClientHttpRequestExecution, com.alibaba.csp.sentinel.slots.block.BlockException)
     *
     * @param request
     * @param body
     * @param execution
     * @param ex
     * @return
     */
    public static SentinelClientHttpResponse handleBlock(HttpRequest request, byte[] body, ClientHttpRequestExecution execution, BlockException ex) {
        return new SentinelClientHttpResponse("handleBlock");
    }

    /**
     * 异常降级业务逻辑
     *
     * 当使用 RestTemplate 调用被 Sentinel 熔断后,发生 BlockException 才会触发
     *
     * @see com.alibaba.csp.sentinel.slots.block.degrade.DegradeException
     * @see com.alibaba.cloud.sentinel.custom.SentinelProtectInterceptor#handleBlockException(org.springframework.http.HttpRequest, byte[], org.springframework.http.client.ClientHttpRequestExecution, com.alibaba.csp.sentinel.slots.block.BlockException)
     *
     * @param request
     * @param body
     * @param execution
     * @param ex
     * @return
     */
    public static SentinelClientHttpResponse handleFallback(HttpRequest request, byte[] body,
                                                            ClientHttpRequestExecution execution, BlockException ex) {
        return new SentinelClientHttpResponse("handleFallback");
    }
}
