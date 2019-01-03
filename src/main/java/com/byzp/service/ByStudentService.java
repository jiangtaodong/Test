package com.byzp.service;

import com.byzp.pojo.ByCount;
import com.byzp.pojo.ByStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ByStudentService {

    int insertuser(ByStudent byStudent);

    List<ByStudent> selectallstudent();

    List<ByStudent> studentclsses();

    List<ByCount> selectstudentinfobyclasses(String classes);

    List<ByCount> likestudentinfo(ByCount byCount);

    int selectallstudentcount();

    List<ByStudent> pagecount(@Param("startrows") int startrows, @Param("countrows") int countrows);

    List<Map<ByStudent,ByStudent>> pagecount1(@Param("startrows") int startrows, @Param("countrows") int countrows);

    List<ByStudent> pagecount2(@Param("startrows") int startrows, @Param("countrows") int countrows);

    List<ByStudent> selectstudentcount();

    List<ByStudent> selectstudentcount2();

    List<ByStudent> selectsc();

    List<ByStudent> countstudent();



}
