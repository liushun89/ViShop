package com.vishop.web.manage.user.controller;

import com.vishop.entity.user.User;
import com.vishop.service.user.PermissionService;
import com.vishop.service.user.RoleService;
import com.vishop.service.user.UserRoleService;
import com.vishop.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with vishop.
 * 用户管理
 * User : Homiss
 * Date : 2016/1/21
 * Time : 17:20
 * REST API 功能/作用：
 * GET 请求 /api/user/ 返回用户的列表
 * GET 请求  /api/user/1 返回ID为1的用户
 * POST 请求 /api/user/ 以用户对象的JSON格式创建新的用户
 * PUT 请求 /api/user/3 以用户对象作为JSON更新ID为3的用户
 * DELETE 请求 /api/user/4 删除ID为4的用户
 * DELETE 请求 /api/user/ 删除所有的用户
 */
@Controller
@RequestMapping("/admin")
public class UserManageController {

    @Resource
    private UserService userService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private RoleService roleService;

    // TODO 用户列表获取
    // TODO 用户冻结
    // TODO 用户角色管理
    // TODO 角色权限管理

    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public String userList(Model model) {
        List<User> users = userService.list();

        return "";
    }


}
