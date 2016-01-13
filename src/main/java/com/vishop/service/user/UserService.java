package com.vishop.service.user;

import com.vishop.IDao.BaseMapper;
import com.vishop.IDao.user.UserMapper;
import com.vishop.entity.user.User;
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

    User selectByNameAndPass(String username,String password){
        return userMapper.selectByNameAndPass(username, password);
    }

    public User loadByUsername(String username) {
        return userMapper.loadByUsername(username);
    }

    @Override
    protected BaseMapper getMapper() {
        return userMapper;
    }


    public boolean usernameIsExist(String username) {
        return userMapper.usernameIsExist(username) > 0 ? true : false;
    }
}
