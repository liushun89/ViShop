package com.vishop.service.user;

import com.vishop.IDao.BaseMapper;
import com.vishop.IDao.user.PermissionMapper;
import com.vishop.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Homiss on 2016/1/11.
 */
@Service
public class PermissionService extends BaseService {

    @Resource
    private PermissionMapper permissionMapper;

    public List<String> loadPerssionsById(Integer userId){
        return permissionMapper.loadPerssionsById(userId);
    }

    @Override
    protected BaseMapper getMapper() {
        return permissionMapper;
    }

}
