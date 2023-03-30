package com.stadium.dao;

import com.stadium.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {
    @Select("select * from user where username = #{username} and password = #{password} and identity = #{identity}")
    User getUser(@Param("username") String username, @Param("password") String password, @Param("identity") String identity);

    @Select("select * from user")
    List<User> selectAll();

    @Select("select * from user where username = #{username}")
    User SelectByUsername(String username);

    @Insert("insert into user(username,password,telephone,identity) value(#{username}, #{password}, #{telephone}, #{identity})")
    void add(User user);

    @Update("update user set username = #{username}, password = #{password}, telephone = #{telephone} where userid = #{userid}")
    void update(User user);

}
