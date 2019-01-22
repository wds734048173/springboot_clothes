package org.lanqiao.clothes.interceptors;

import org.lanqiao.clothes.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Auther: WDS
 * @Date: 2019/1/22 23:38
 * @Description:
 */
public class UserInterceptor implements HandlerInterceptor {
    //    拦截器，拦截已注册账号却未完善自己店铺信息的商家，未完善则转到个人商铺信息页面去完善
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user.getStoreId() !=-1){
            return true;
        }
        request.getRequestDispatcher("/manager/store").forward(request,response);
        return false;
    }
}
