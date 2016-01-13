package com.vishop.web.front.user.controller;

import com.vishop.entity.common.MapContainer;
import com.vishop.entity.user.User;
import com.vishop.entity.user.UserRole;
import com.vishop.service.user.UserRoleService;
import com.vishop.service.user.UserService;
import com.vishop.web.front.user.form.LoginForm;
import com.vishop.web.front.user.form.RegistForm;
import com.vishop.web.front.user.form.validator.UserFormValidator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Homiss on 2016/1/11.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private UserRoleService userRoleService;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(String msg, Model model){
        if("logout".equals(msg)){
            model.addAttribute("msg", "您已登出。");
        } else if("unauthenticated".equals(msg)){
            model.addAttribute("msg", "你没有当前操作权限");
        }
        return "front/common/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(LoginForm form, Model model, HttpServletRequest request){
        MapContainer result = UserFormValidator.validateLogin(form);
        if(!result.isEmpty()){
            model.addAllAttributes(result);
            return "front/common/login";
        }
        // TODO 用户登录验证
        User user = userService.loadByUsername(form.getUsername());
        if(user == null){
            model.addAttribute("username", "用户名不存在");
            return "front/common/login";
        }
        UsernamePasswordToken token = new UsernamePasswordToken(form.getUsername(), form.getPassword());

        /*if("1".equals(isRemenber)){
            token.setRememberMe(true);
        }*/
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
            SavedRequest savedRequest = WebUtils.getSavedRequest(request);
            if(savedRequest != null){
                // 获取登录前的页面
                savedRequest.getRequestURI();
            }
            Session session = currentUser.getSession();
            User u = new User();
            u.setId(user.getId());
            session.setAttribute("user", u);
        } catch (UnknownAccountException ex) {
            model.addAttribute("username", "用户名不存在");
        } catch (IncorrectCredentialsException ex) {
            model.addAttribute("password", "密码错误");
        } catch (LockedAccountException ex) {
            model.addAttribute("error", "账号已被锁定，请与系统管理员联系");
        } catch (AuthenticationException ex) {
            model.addAttribute("error", "您没有授权!");
        }
        return "front/index";
    }

    @RequestMapping(value = "/regist", method = RequestMethod.GET)
    public String regist(){
        return "jsp/front/common/regist";
    }

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public String regist(RegistForm form, Model model){
        MapContainer result = UserFormValidator.validateRegist(form);
        if(!result.isEmpty()){
            model.addAllAttributes(result);
            return "front/common/regist";
        }
        if(userService.usernameIsExist(form.getUsername())){
            model.addAttribute("username", "用户名已存在");
            return "front/common/regist";
        }
        User user = new User();
        user.setUsername(form.getUsername());
        user.setPassword(form.getPassword());
        user.setCreatedTime(System.currentTimeMillis());

        userService.insert(user);

        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(4);
        userRoleService.insert(userRole);

        return "front/common/login";
    }

    @RequestMapping(value = "/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "redirect:/user/login?msg=logout";
    }
}
