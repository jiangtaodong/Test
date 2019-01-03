package com.byzp.service.serviceimpl;

import com.byzp.mapper.ByStudentMapper;
import com.byzp.pojo.ByCount;
import com.byzp.pojo.ByStudent;
import com.byzp.service.ByStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ByStudentServiceimpl implements ByStudentService {

    @Autowired
    private ByStudentMapper byStudentMapper;

    @Override
    public int insertuser(ByStudent byStudent) {

        int result = byStudentMapper.insertuser(byStudent);

        return result;

    }

    @Override
    public List<ByStudent> selectallstudent() {

        List<ByStudent> selectallstudent = byStudentMapper.selectallstudent();

        return selectallstudent;
    }

    @Override
    public List<ByStudent> studentclsses() {

        List<ByStudent> studentclsses = byStudentMapper.studentclsses();

        return studentclsses;
    }

    @Override
    public List<ByCount> selectstudentinfobyclasses(String classes) {

        List<ByCount> selectstudentinfobyclasses = byStudentMapper.selectstudentinfobyclasses(classes);

        return selectstudentinfobyclasses;
    }

    @Override
    public List<ByCount> likestudentinfo(ByCount byCount) {

        List<ByCount> likestudentinfo = byStudentMapper.likestudentinfo(byCount);

        return likestudentinfo;
    }

    @Override
    public int selectallstudentcount() {

        int selectallstudentcount = byStudentMapper.selectallstudentcount();

        return selectallstudentcount;
    }

    @Override
    public List<ByStudent> pagecount(int startrows, int countrows) {

        List<ByStudent> pagecount = byStudentMapper.pagecount(startrows,countrows);

        return pagecount;
    }

    @Override
    public List<Map<ByStudent,ByStudent>> pagecount1(int startrows, int countrows) {

        List<Map<ByStudent,ByStudent>> pagecount1 = byStudentMapper.pagecount1(startrows,countrows);

        return pagecount1;
    }

    @Override
    public List<ByStudent> pagecount2(int startrows, int countrows) {

        List<ByStudent> pagecount2 = byStudentMapper.pagecount2(startrows,countrows);

        return pagecount2;
    }

    @Override
    public List<ByStudent> selectstudentcount() {

        List<ByStudent> selectstudentcount = byStudentMapper.selectstudentcount();

        return selectstudentcount;
    }

    @Override
    public List<ByStudent> selectstudentcount2() {

        List<ByStudent> selectstudentcount2 = byStudentMapper.selectstudentcount2();

        return selectstudentcount2;
    }

    @Override
    public List<ByStudent> selectsc() {

        List<ByStudent> selectsc = byStudentMapper.selectsc();

        return selectsc;
    }

    @Override
    public List<ByStudent> countstudent() {

        List<ByStudent> countstudent = byStudentMapper.countstudent();

        return countstudent;
    }
}
