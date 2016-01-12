package com.vishop.IDao;

import java.util.List;

public interface BaseMapper{

  /**
   * 新增记录
   * 
   * @param t
   */
  public <T> int insert(T t);

  /**
   * 根据ID查询记录
   * 
   * @param id
   * @return
   */
  <T> T loadById(Integer id);


  <T> List<T> list();

  /**
   * 根据ID删除记录
   * 
   * @param id
   */
  <T> int deleteById(Integer id);

  /**
   * 更新记录
   * 
   * @param t
   */
  <T> int update(T t);

  /**
   * 获取记录总数
   */
  long count();

}
