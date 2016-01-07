package com.vishop.IDao.user;

import com.vishop.IDao.BaseMapper;
import com.vishop.entity.user.User;
import org.apache.ibatis.annotations.Param;

/**
 * UserMapper
 *
 * @author Homiss
 * @date 2016/1/7
 */
public interface UserMapper extends BaseMapper{

    User selectByNameAndPass(@Param("username")String username,@Param("password")String password);

}
