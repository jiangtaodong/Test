package com.byzp.service.serviceimpl;

import com.byzp.mapper.ByCountMapper;
import com.byzp.pojo.ByCount;
import com.byzp.service.ByCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ByCountServiceimpl implements ByCountService{

    @Autowired
    private ByCountMapper byCountMapper;

    @Override
    public List<ByCount> selectallstudentcount() {

        List<ByCount> selectallstudentcount = byCountMapper.selectallstudentcount();

        return selectallstudentcount;
    }

    @Override
    public ByCount studentbukaoxinxi(int student_number) {

        ByCount studentbukaoxinxi = byCountMapper.studentbukaoxinxi(student_number);

        return studentbukaoxinxi;
    }

    @Override
    public int insertstudentnumber(String student_number) {

        int insertstudentnumber = byCountMapper.insertstudentnumber(student_number);

        return insertstudentnumber;
    }

    @Override
    public ByCount selectstudentinfobystudent_number(int student_number) {

        ByCount selectstudentinfobystudent_number = byCountMapper.selectstudentinfobystudent_number(student_number);

        return selectstudentinfobystudent_number;
    }

    @Override
    public int updatecountbystudent_number(ByCount byCount) {

        int updatecountbystudent_number = byCountMapper.updatecountbystudent_number(byCount);

        return updatecountbystudent_number;
    }

    @Override
    public List<ByCount> chaxunbanjihejigerenshu() {

        List<ByCount> chaxunbanjihejigerenshu = byCountMapper.chaxunbanjihejigerenshu();

        return chaxunbanjihejigerenshu;
    }

}
