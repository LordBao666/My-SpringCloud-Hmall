package com.hmall.gateway.filters;


import com.hmall.common.exception.UnauthorizedException;
import com.hmall.gateway.config.AuthProperties;
import com.hmall.gateway.utils.JwtTool;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2025/3/11 11:59
 * @Version 1.0
 */
@Component
@RequiredArgsConstructor
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private final JwtTool jwtTool;

    private final AuthProperties authProperties;


    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.得到请求
        ServerHttpRequest request = exchange.getRequest();


        //2.判断请求是否需要用户登录校验.如果不需要,直接放行
        if(isExclude(request.getPath().toString())){
            //放行
            return chain.filter(exchange);
        }

        //3.解析token
        String token=null;
        List<String> headers =request.getHeaders().get("authorization");
        if(headers!=null && !headers.isEmpty()){
            token = headers.get(0);
        }
        Long userId = null;
        try {
            userId = jwtTool.parseToken(token);
        }catch (UnauthorizedException exception){
            //校验失败,设置响应码为401,拦截该请求
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }


        //4. 传递用户信息(重新创建一个请求,将user-info放到请求头中)
        String userinfo = userId.toString();
        ServerWebExchange swe = exchange.mutate().request(builder -> builder.header("user-info", userinfo)).build();
        //5.放行
        return chain.filter(swe);
    }

    private boolean isExclude(String requestPath) {
        List<String> excludePaths = authProperties.getExcludePaths();
        for(String pathPattern:excludePaths){
            if(antPathMatcher.match(pathPattern,requestPath)){
                return true;
            }
        }
        return false;
    }

    //值越小优先级越高,保证比NettyRoutingFilter高即可
    @Override
    public int getOrder() {
        return 0;
    }
}
