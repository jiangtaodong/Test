package com.byzp.mapper;

import com.byzp.pojo.ByCount;

import java.util.List;

public interface ByCountMapper {

    List<ByCount> selectallstudentcount();

    ByCount studentbukaoxinxi(int student_number);

    int insertstudentnumber(String student_number);

    ByCount selectstudentinfobystudent_number(int student_number);

    int updatecountbystudent_number(ByCount byCount);

    List<ByCount> chaxunbanjihejigerenshu();

}