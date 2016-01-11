package com.vishop.service.user;

import com.vishop.IDao.BaseMapper;
import com.vishop.IDao.user.RoleMapper;
import com.vishop.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Homiss on 2016/1/11.
 */
@Service
public class RoleService extends BaseService {

    @Resource
    private RoleMapper roleMapper;

    public List<String> loadRolesById(Integer userId) {
        return roleMapper.loadRolesById(userId);
    }


    @Override
    protected BaseMapper getMapper() {
        return roleMapper;
    }
}
