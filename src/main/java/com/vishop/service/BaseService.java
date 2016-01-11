package com.vishop.service;

import com.vishop.IDao.BaseMapper;
import com.vishop.entity.common.BaseEntity;

import java.util.List;

public abstract class BaseService{

  public <T extends BaseEntity> int insert(T t){
    return getMapper().insert(t);
  }

  public <T extends BaseEntity> T loadById(Integer id){
    return getMapper().loadById(id);
  }

  public <T> List<T> list(){
    return getMapper().list();
  }

  public <T extends BaseEntity> int update(T t){
    return getMapper().update(t);
  }

  public int deleteById(Integer id){
    return getMapper().deleteById(id);
  }

  public long count(){
    return getMapper().count();
  }

  protected abstract BaseMapper getMapper();

}