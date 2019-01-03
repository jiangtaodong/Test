package com.byzp.controller;

import com.byzp.mapper.ByCountMapper;
import com.byzp.pojo.ByCount;
import com.byzp.service.ByCountService;
import com.byzp.service.ByLoginService;
import com.byzp.service.ByStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CountController {

    @Autowired
    private ByLoginService byLoginService;

    @Autowired
    private ByStudentService byStudentService;

    @Autowired
    private ByCountMapper byCountMapper;

    @Autowired
    private ByCountService byCountService;

    /**
     * 查询学生详细信息
     */
    @RequestMapping("count/selectallstudentcountinfo")
    @ResponseBody
    public List<ByCount> selectallstudentcountinfo(){

        List<ByCount> selectallstudentcountinfo = byCountMapper.selectallstudentcount();

        return selectallstudentcountinfo;

    }

    /**
     * 学生补考信息查询
     */
    @RequestMapping("count/studentbukaoxinxichaxun")
    @ResponseBody
    public ByCount studentbukaoxinxichaxun(int student_number){

        ByCount studentbukaoxinxichaxun = byCountMapper.studentbukaoxinxi(student_number);

        return studentbukaoxinxichaxun;
    }

    /**
     * 根据student_number查询学生详细信息
     */
    @RequestMapping("count/selectstudentinfobystudent_number")
    @ResponseBody
    public ByCount selectstudentinfobystudent_number(int student_number){

        ByCount selectstudentinfobystudent_number = byCountMapper.selectstudentinfobystudent_number(student_number);

        return selectstudentinfobystudent_number;
    }

    /**
     * 根据student_number添加学生成绩
     */
    @RequestMapping("count/updatecountbystudent_number")
    public String updatecountbystudent_number(ByCount byCount){

        int updatecountbystudent_number = byCountMapper.updatecountbystudent_number(byCount);

        if(updatecountbystudent_number == 1){

            System.out.println("添加学生成绩成功！【" + byCount.getStudent_number() + "】");

        }else{

            System.out.println("添加学生成绩失败！");

        }

       /* return "forward:/WEB-INF/pages/homepage-teacher.jsp";*/
        return "redirect:/backhomepage";
    }

    /**
     * 返回首页
     */
    @RequestMapping("backhomepage")
    public String backhomepage(){

        return "homepage-teacher";

    }

    /**
     * 查询班级和及格人数
     */
    @RequestMapping("js/shujutongji/student/chaxunbanjihejigerenshu")
    @ResponseBody
    public List<ByCount> chaxunbanjihejigerenshu(){

        List<ByCount> chaxunbanjihejigerenshu = byCountService.chaxunbanjihejigerenshu();

        return chaxunbanjihejigerenshu;

    }

}
