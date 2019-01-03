package com.byzp.service;

import com.byzp.pojo.ByCount;

import java.util.List;

public interface ByCountService {

    List<ByCount> selectallstudentcount();

    ByCount studentbukaoxinxi(int student_number);

    int insertstudentnumber(String student_number);

    ByCount selectstudentinfobystudent_number(int student_number);

    int updatecountbystudent_number(ByCount byCount);

    List<ByCount> chaxunbanjihejigerenshu();

}
