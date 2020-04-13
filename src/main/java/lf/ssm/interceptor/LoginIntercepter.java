package lf.ssm.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**对登录进行拦截的拦截器
 * @Classname LoginIntercepter
 * @Date 2019/4/19 20:09
 * @Created by liufeng
 */

@Slf4j
public class LoginIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uri = request.getRequestURI();
        if(!"/login".contains(uri)){ //对包含/login的请求放行
            if(request.getSession().getAttribute("user")==null) {
                //String contentPath = request.getContextPath() + "/login";
                //log.info("请先登录" + contentPath);
                //response.sendRedirect(login);
                //return false;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
