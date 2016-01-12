package com.vishop.service.user;

import com.vishop.IDao.BaseMapper;
import com.vishop.IDao.user.RolePermissionMapper;
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
public class RolePermission extends BaseService {

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    protected BaseMapper getMapper() {
        return rolePermissionMapper;
    }
}
