package com.springboot.dao;



import com.springboot.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

public interface UserRepository {

    /**
     * 插入数据
     */
    //方式1：
    @Insert("insert into user1(name,name_type,user_password) values(#{name,jdbcType=VARCHAR},#{name_type,jdbcType=VARCHAR},#{user_password,jdbcType=VARCHAR})")
    int insertByMap(Map<String,Object> map);

    //方式2：
    @Insert("insert into user1(name,name_type,user_password) values(#{name,jdbcType=VARCHAR},#{name_type,jdbcType=VARCHAR},#{user_password,jdbcType=VARCHAR})")
    int insertByObject(User user1);

    /**
     * 查询数据
     */
    //方式1：查询到一条记录
    @Select("SELECT * FROM user1 WHERE name=#{name}")
    User findAllOfOne(String name);
    //方式2：查询到一条记录中的name
    @Select("SELECT name FROM user1 WHERE name_type=#{name_type}")
    String findByName(String name_type);

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
