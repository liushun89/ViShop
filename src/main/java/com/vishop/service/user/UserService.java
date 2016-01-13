package com.vishop.service.user;

import com.vishop.IDao.BaseMapper;
import com.vishop.IDao.user.UserMapper;
import com.vishop.IDao.user.UserRoleMapper;
import com.vishop.core.util.MD5Util;
import com.vishop.core.util.SaltUtil;
import com.vishop.entity.user.User;
import com.vishop.entity.user.UserRole;
import com.vishop.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService
 *
 * @author Homiss
 * @date 2016/1/7
 */
@Service
public class UserService extends BaseService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    protected BaseMapper getMapper() {
        return userMapper;
    }

    User selectByNameAndPass(String username,String password){
        return userMapper.selectByNameAndPass(username, password);
    }

    public User loadByUsername(String username) {
        return userMapper.loadByUsername(username);
    }

    public boolean usernameIsExist(String username) {
        return userMapper.usernameIsExist(username) > 0 ? true : false;
    }

    public void regist(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setSalt(SaltUtil.makeSalt());
        user.setPassword(MD5Util.calc(password, user.getSalt()));
        user.setCreatedTime(System.currentTimeMillis());
        userMapper.insert(user);

        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(4);
        userRoleMapper.insert(userRole);
    }
}
