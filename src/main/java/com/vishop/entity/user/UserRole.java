package com.vishop.entity.user;

import com.vishop.entity.common.BaseEntity;
import lombok.Data;

@Data
public class UserRole extends BaseEntity{

    private static final long serialVersionUID = -760065436583667943L;

    private Integer userId;

    private Integer roleId;

}