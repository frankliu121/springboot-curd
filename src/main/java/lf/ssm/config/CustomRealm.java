package lf.ssm.config;

import lf.ssm.entity.sys.User;
import lf.ssm.exception.PermissionsException;
import lf.ssm.service.sys.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @Classname CustomRealm
 * @Date 2020/4/15 14:45
 * @Created by liufeng
 */

@Component
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    /**
     * 认证
     * 获取即将需要认证的信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
        System.out.println("-------身份认证方法:根据令牌获取登录信息--------");
        String userName = (String) authenticationToken.getPrincipal();
        String userPwd = new String((char[]) authenticationToken.getCredentials());
        User user = userService.selectByUsername(userName);

        // todo 密码加密处理
        if (user == null){ //未知账号
            throw new IncorrectCredentialsException();
        } else if (!userPwd.equals(user.getPassword() )) { //密码错误
            throw new IncorrectCredentialsException();
        }
        // 这里传的是加密的还是不加密的
        return new SimpleAuthenticationInfo(userName, user.getPassword(),getName());
    }


    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> stringSet = new HashSet<>();
        stringSet.add("user:show");
        stringSet.add("user:admin");
        info.setStringPermissions(stringSet);
        return info;
    }
}
