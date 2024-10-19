package com.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.pojo.User;

@Mapper
public interface UserMapper {
    //根据用户名查询用户
    @Select("select * from user where username=#{username} ")
    User findByUserName(String username);
    //添加
    @Insert("insert into user(username,password,create_time,update_time) values(#{username} ,#{password} ,now(),now())")
    void add(@Param("username") String username, @Param("password") String password);

}
