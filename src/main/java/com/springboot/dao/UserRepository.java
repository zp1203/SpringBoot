package com.springboot.dao;



import com.springboot.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface UserRepository {

    /**
     * 插入数据
     */
    //方式1：
    @Insert("insert into user1(name,name_type,user_password,create_time,update_time) values (#{name,jdbcType=VARCHAR},#{name_type,jdbcType=VARCHAR},#{user_password,jdbcType=VARCHAR},#{create_time,jdbcType=DATE},#{update_time,jdbcType=DATE})")
    int insertByMap(Map<String,Object> map);

    //方式2：
    @Insert("insert into user1(name,name_type,user_password,create_time,update_time) values (#{name,jdbcType=VARCHAR},#{name_type,jdbcType=VARCHAR},#{user_password,jdbcType=VARCHAR},#{create_time,jdbcType=DATE},#{update_time,jdbcType=DATE})")
    int insertByObject(User user1);

    /**
     * 查询数据
     */
    //方式1：查询到一条记录
    @Select("SELECT * FROM user1 WHERE name=#{name}")
    List<User> findAll(String name);
    //方式2：查询到一条记录中的name
    @Select("SELECT name FROM user1 WHERE name_type=#{name_type}")
    List<String> findByName(String name_type);

    /**
     * 修改（更新）
     */
    @Update("update user1 set name = #{newName} where name=#{oldName}")
    int updateByName(String newName,String oldName);


    /**
     * 删除
     */
    @Delete("delete from user1 where name=#{name}")
    int deleteByName(String name);



}
