package com.vishop.entity.user;

import com.vishop.entity.common.BaseEntity;
import lombok.Data;

/**
 * UserEntity
 *
 * @author Homiss
 * @date 2016/1/7
 */

@Data
public class User extends BaseEntity {

    private static final long serialVersionUID = 9022091804016860892L;

    private String username;
    private String password;
    private String salt;
    private Long createdTime;
    private Long updateTime;
    private Role role;

}
