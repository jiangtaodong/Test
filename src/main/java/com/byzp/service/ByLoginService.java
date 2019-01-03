package com.byzp.service;

import com.byzp.pojo.ByLogin;
import com.byzp.pojo.ByStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ByLoginService {

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

    ByLogin checkloginidentity(ByLogin username);

    List<ByLogin> selectidentity();

    List<ByLogin> selectloginbyidentity(String identity);

}
