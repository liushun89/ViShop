package com.vishop.entity.common;

import lombok.*;

import java.io.Serializable;

/**
 * IdEntity
 *
 * @author Homiss
 * @date 2016/1/7
 */

@Data
public class BaseEntity implements Serializable{

    private static final long serialVersionUID = -5395055192804212273L;
    private Integer id;

}
