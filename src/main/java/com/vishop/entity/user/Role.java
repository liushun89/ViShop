package com.vishop.entity.user;

import com.vishop.entity.common.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * Role
 *
 * @author Homiss
 * @date 2016/1/7
 */

@Data
public class Role extends BaseEntity {

    private static final long serialVersionUID = -1648480256365420244L;
    private String roleName;
    private Long createdTime;
    private Long updateTime;
    private List<Permission> permission;

}
