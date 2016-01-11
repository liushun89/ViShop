package com.vishop.IDao.user;

import com.vishop.IDao.BaseMapper;

import java.util.List;

public interface PermissionMapper extends BaseMapper {

    List<String> loadPerssionsById(Integer userId);

}