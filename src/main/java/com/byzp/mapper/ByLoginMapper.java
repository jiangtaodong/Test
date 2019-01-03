package com.byzp.mapper;

import com.byzp.pojo.ByLogin;
import com.byzp.pojo.ByStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ByLoginMapper {

    int login(ByLogin byLogin);

    int selectloginusernamebyusername(String username);

    List<ByLogin> selectall();

    int updatepassword(@Param("username")String username, @Param("password")String password);

    int insertbyloginuser(ByStudent byStudent);

    int selectusername(String username);

    int deleteloginuserbyid(int id);

    int deleteuserbyid(int id);

    ByLogin selectuserbyid(int id);

    int updateuserbyid(ByLogin byLogin);

    List<ByLogin> selectidentity();

    List<ByLogin> selectloginbyidentity(String identity);

    int deleteByPrimaryKey(Integer id);

    int insert(ByLogin record);

    int insertSelective(ByLogin record);

    ByLogin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ByLogin record);

    int updateByPrimaryKey(ByLogin record);

    ByLogin checkloginidentity(ByLogin username);
}