package com.gateway.service.filter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @Description
 * @Project spring-cloud-application
 * @Package com.gateway.service.filter
 * @Author xuzhenkui
 * @Date 2020/4/30 22:17
 */
@Configuration
public class GatewayFilter implements GlobalFilter, Ordered {
    private Logger logger = LoggerFactory.getLogger(GatewayFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        List<String> list = exchange.getRequest().getHeaders().get("Authorization");
        if (list == null || list.size() == 0 || StringUtils.isBlank(list.get(0))){
            logger.info("--------- IP: " + exchange.getRequest().getRemoteAddress() + "; URI: " +
                    exchange.getRequest().getURI() + "; token 缺失，非法请求 ---------");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
