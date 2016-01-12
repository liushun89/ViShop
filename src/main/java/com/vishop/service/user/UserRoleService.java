package com.vishop.service.user;

import com.vishop.IDao.BaseMapper;
import com.vishop.IDao.user.UserRoleMapper;
import com.vishop.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with vishop.
 * User : Homiss
 * Date : 2016/1/12
 * Time : 21:13
 */
@Service
public class UserRoleService extends BaseService {

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    protected BaseMapper getMapper() {
        return userRoleMapper;
    }
}
