package com.vishop.IDao.user;

import com.vishop.IDao.BaseMapper;

import java.util.List;

public interface RoleMapper extends BaseMapper {

    List<String> loadRolesById(Integer userId);


}