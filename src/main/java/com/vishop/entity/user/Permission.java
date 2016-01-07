package com.vishop.entity.user;

import com.vishop.entity.common.BaseEntity;
import lombok.Data;

/**
 * Permission
 *
 * @author Homiss
 * @date 2016/1/7
 */

@Data
public class Permission extends BaseEntity {

    private static final long serialVersionUID = 3136077506680159023L;
    private String permissionName;
    private Long createdTime;
    private Long updateTime;

}
