package com.vishop.entity.user;

import com.vishop.entity.common.BaseEntity;
import lombok.Data;

@Data
public class RolePermission extends BaseEntity {

    private static final long serialVersionUID = 495335866631212083L;

    private Integer permissionId;

    private Integer roleId;

}