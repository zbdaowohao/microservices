package com.ads.getway.config;


import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.cloud.gateway.support.DefaultServerRequest;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * 转发之前拦截 白名单过滤 鉴权
 */
//先取消加载@Component
@Slf4j
public class TokenFilter implements GlobalFilter, Ordered {

    // 白名单 排除无需验证的 token
    private static final String[] whiteList = {"/auth/login", "/auth/logout", "/auth/reset", "/auth/kaptcha"};


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        String url = serverHttpRequest.getURI().getPath();
        log.info("获取请求的url：{}", url);
        if (Arrays.asList(whiteList).contains(url)) {
            log.info("白名单url：" + url + "，直接放行");
            return chain.filter(exchange);
        }
        String token = serverHttpRequest.getQueryParams().getFirst("token");
        log.info("获取请求的token：{}", token);
        if (StringUtils.isBlank(token)) {
            return setResponse(exchange, "鉴权失败，token为空");
        }
        return chain.filter(exchange);
    }


    /**
     * 设置拦截返回信息
     *
     * @param exchange
     * @param msg
     * @return
     */
    private Mono<Void> setResponse(ServerWebExchange exchange, String msg) {
        ServerHttpResponse originalResponse = exchange.getResponse();
        originalResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
        originalResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        byte[] response = null;
        try {
            response = JSON.toJSONString(msg).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        DataBuffer buffer = originalResponse.bufferFactory().wrap(response);
        return originalResponse.writeWith(Flux.just(buffer));
    }

    @Override
    public int getOrder() {
        return -200;
    }

}
