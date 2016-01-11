package com.vishop.service.shiro;

import com.vishop.entity.user.User;
import com.vishop.service.user.PermissionService;
import com.vishop.service.user.RoleService;
import com.vishop.service.user.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

public class UserRealm extends AuthorizingRealm {

	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	@Resource
	private PermissionService permissionService;

	/**
	 * 获取授权信息
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String currentUsername = (String) principals.fromRealm(getName()).iterator()
				.next();
		if (!StringUtils.isEmpty(currentUsername)) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			User user = userService.loadByUsername(currentUsername);
			addRole(user.getId(), info);
			return info;
		}
		return null;
	}

	/**
	 * @param authcToken
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String username = token.getUsername();
		if (!StringUtils.isEmpty(username)) {
			User user = userService.loadByUsername(username);
			if (user != null) {
				return new SimpleAuthenticationInfo(user.getUsername(),
						user.getPassword(), getName());
			}
		}
		return null;
	}

	private void addRole(Integer userId, SimpleAuthorizationInfo info) {
		List<String> roles = roleService.loadRolesById(userId);
		for (String role : roles) {
			info.addStringPermission(role);
			addPerssion(userId, info);
		}
	}


	private void addPerssion(Integer userId, SimpleAuthorizationInfo info) {
		List<String> perssions = permissionService.loadPerssionsById(userId);
		for (String perssion : perssions) {
			info.addStringPermission(perssion);
		}
	}
}