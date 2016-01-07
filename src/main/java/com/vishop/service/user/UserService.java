package com.vishop.service.user;

import com.vishop.IDao.BaseMapper;
import com.vishop.IDao.user.UserMapper;
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

    @Override
    protected BaseMapper getMapper() {
        return null;
    }
}
