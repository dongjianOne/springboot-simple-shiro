package com.dong.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author dongjian
 * @Date 2020/2/16 15:14
 * @Description:
 * @Version:1.0
 */
@Controller
public class MyController {
    @RequestMapping({"/", "/index"})
    public String toIndex(Model model) {
        model.addAttribute("msg", "hello,shiro");
        return "index";
    }

    @RequestMapping("/user/add")
    public String add() {
        return "/user/add";
    }

    @RequestMapping("/user/update")
    public String update() {
        return "/user/update";
    }
    @RequestMapping("/user/login")
    public String toLogin(){
        return "/user/login";
    }
    @RequestMapping("/toLogin")
    public String login(String username,String password,Model model){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行登录操作
        try {
            subject.login(token);
            return "index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户名不存在");
            return "/user/login";
        }catch (IncorrectCredentialsException e) {
            model.addAttribute("msg","密码错误");
            return "/user/login";
        }catch (AuthenticationException e) {
            model.addAttribute("msg","认证失败");
            return "/user/login";
        }
    }

    /**
     * responseBody注解的作用是将controller的方法返回的对象通过适当的转换器转换为指定的格式之后
     * 写入到response对象的body区，通常用来返回JSON数据或者是XML数据
     * @return
     */
    @RequestMapping("/unauth")
    @ResponseBody // 输出指定个数的数据
    public String unAuth(){
        return "未经授权无法访问页面！";
    }

    @RequestMapping("/user/logout")
    public String logOut(){
        Subject currentUser =SecurityUtils.getSubject();
        //注销退出
        currentUser.logout();
        return "/user/login";
    }
}
