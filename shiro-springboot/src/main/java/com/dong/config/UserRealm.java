package com.dong.config;

import com.dong.pojo.User;
import com.dong.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * @Author dongjian
 * @Date 2020/2/16 15:42
 * @Description: 自定义的UserRealm 都需要继承AuthorizingRealm 1.认证 2.授权
 * @Version:1.0
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;
    /**
     * 授权 Authorization
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //info.addStringPermission("user:add");
        //info.addStringPermission("user:update");
        Subject subject = SecurityUtils.getSubject();
        // 获取用户对象
        User currentUser = (User) subject.getPrincipal();
        String[] split = currentUser.getPerms().split(":");
        List<String> list = Arrays.asList(split);
        // 授权
        info.addStringPermissions(list);
        return info;
    }

    /**
     * 认证 Authentication
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了=>认证doGetAuthorizationInfo");


        //比较用户名
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 去数据库里取真实的数据
        User user = userService.queryByName(token.getUsername());
        // 没有这个用户
        if (null == user){
            // 抛出异常 UnknownAccountException
            return null;
        }
        // 判断是否已登录
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("loginUser",user);
        // 密码认证 shiro做~ 不需要自己认证
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
