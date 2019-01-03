package com.byzp.service.serviceimpl;

import com.byzp.mapper.ByLoginMapper;
import com.byzp.pojo.ByLogin;
import com.byzp.pojo.ByStudent;
import com.byzp.service.ByLoginService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ByLoginServiceimpl implements ByLoginService{

    @Autowired
    private ByLoginMapper byLoginMapper;

    @Override
    public int login(ByLogin byLogin) {

        int login = byLoginMapper.login(byLogin);

        return login;
    }

    @Override
    public int selectloginusernamebyusername(String username) {

        int selectloginusernamebyusername = byLoginMapper.selectloginusernamebyusername(username);

        return selectloginusernamebyusername;
    }

    @Override
    public List<ByLogin> selectall() {

        List<ByLogin> selectall = byLoginMapper.selectall();

        return selectall;
    }

    @Override
    public int updatepassword(@Param("username")String username, @Param("password")String password) {

        int updatepassword = byLoginMapper.updatepassword(username,password);

        return updatepassword;
    }

    @Override
    public int insertbyloginuser(ByStudent byStudent) {

        int insertbyloginuser = byLoginMapper.insertbyloginuser(byStudent);

        return insertbyloginuser;
    }

    @Override
    public int selectusername(String username) {

        int selectusername = byLoginMapper.selectusername(username);

        return selectusername;
    }

    @Override
    public int deleteloginuserbyid(int id) {

        int deleteloginuserbyid =  byLoginMapper.deleteloginuserbyid(id);

        return deleteloginuserbyid;
    }

    @Override
    public int deleteuserbyid(int id) {

        int deleteuserbyid = byLoginMapper.deleteuserbyid(id);

        return deleteuserbyid;

    }

    @Override
    public ByLogin selectuserbyid(int id) {

        ByLogin selectuserbyid = byLoginMapper.selectuserbyid(id);

        return selectuserbyid;
    }

    @Override
    public int updateuserbyid(ByLogin byLogin) {

        int updateuserbyid = byLoginMapper.updateuserbyid(byLogin);

        return updateuserbyid;
    }

    @Override
    public ByLogin checkloginidentity(ByLogin username) {

        ByLogin checkusername =  byLoginMapper.checkloginidentity(username);

        return checkusername;
    }

    @Override
    public List<ByLogin> selectidentity() {

        List<ByLogin > selectidentity = byLoginMapper.selectidentity();

        return selectidentity;
    }

    @Override
    public List<ByLogin> selectloginbyidentity(String identity) {

        List<ByLogin> selectloginbyidentity = byLoginMapper.selectloginbyidentity(identity);

        return selectloginbyidentity;
    }

}
