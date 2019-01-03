package com.byzp.mapper;

import com.byzp.pojo.ByCount;
import com.byzp.pojo.ByStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ByStudentMapper {

    int insertuser(ByStudent byStudent);

    List<ByStudent> selectallstudent();

    List<ByCount> selectstudentinfobyclasses(String classes);

    int deleteByPrimaryKey(Integer id);

    int insert(ByStudent record);

    int insertSelective(ByStudent record);

    ByStudent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ByStudent record);

    int updateByPrimaryKey(ByStudent record);

    List<ByStudent> studentclsses();

    List<ByCount> likestudentinfo(ByCount byCount);

    int selectallstudentcount();

    List<ByStudent> pagecount(@Param("startrows") int startrows, @Param("countrows") int countrows);

    //resulttype=map
    List<Map<ByStudent,ByStudent>> pagecount1(@Param("startrows") int startrows, @Param("countrows") int countrows);
    //resultMap
    List<ByStudent> pagecount2(@Param("startrows") int startrows, @Param("countrows") int countrows);

    List<ByStudent> selectstudentcount();

    List<ByStudent> selectstudentcount2();

    List<ByStudent> selectsc();

    List<ByStudent> countstudent();

}