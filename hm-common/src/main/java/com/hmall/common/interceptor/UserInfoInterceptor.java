package com.hmall.common.interceptor;


import cn.hutool.core.util.StrUtil;
import com.hmall.common.utils.UserContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Lord_Bao
 * @Date 2025/3/11 15:10
 * @Version 1.0
 */
public class UserInfoInterceptor  implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userInfo = request.getHeader("user-info");

        //如果user-info不为空,则存入到ThreadLocal中
        if(StrUtil.isNotBlank(userInfo)){
            Long userId = Long.valueOf(userInfo);
            UserContext.setUser(userId);
        }

        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清除用户
        UserContext.removeUser();
    }


}
