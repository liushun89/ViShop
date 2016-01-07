package com.vishop.service.shiro;

import com.vishop.entity.user.User;
import com.vishop.service.user.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

public class UserRealm extends AuthorizingRealm {

	@Resource
	private UserService userService;

	/**
	 * 获取授权信息
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String currentUsername = (String) principals.fromRealm(getName()).iterator()
				.next();
		if (!StringUtils.isEmpty(currentUsername)) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			User user = userService.loadById(currentUsername);
			// addRole(userId, info);
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
		String accountName = token.getUsername();
		if (!StringUtils.isEmpty(accountName)) {
			User user = userService.loadById(accountName);
			if (user != null) {
				return new SimpleAuthenticationInfo(user.getUsername(),
						user.getPassword(), getName());
			}
		}
		return null;
	}

	/*private void addRole(Integer userId, SimpleAuthorizationInfo info) {
		List<String> roles = userService.findByUserId(userId);
		for (String role : roles) {
			info.addRole(role);
		}
	}


	private void addPerssion(Integer userId, SimpleAuthorizationInfo info) {
		List<String> perssions = userService.findByUserId(userId);
		for (String perssion : perssions) {
			info.addStringPermission(perssion);
		}
	}*/
}