package com.byzp.controller;

import com.byzp.pojo.ByCount;
import com.byzp.pojo.ByStudent;
import com.byzp.service.ByLoginService;
import com.byzp.service.ByStudentService;
import com.byzp.service.ByTeacherService;
import com.byzp.util.PageCountUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class StudentController {

    @Autowired
    private ByLoginService byLoginService;

    @Autowired
    private ByStudentService byStudentService;

    @Autowired
    private ByTeacherService byTeacherService;

    /**
     * 查询学生详细信息
     */
    @RequestMapping("student/selectallstudentinfo")
    @ResponseBody
    public List<ByStudent> selectallstudentinfo(PageCountUtil pageCountUtil){

        int pagenow = 1;

        //查询学生总条数
        int selectallstudentcount = byStudentService.selectallstudentcount();
        pageCountUtil.setRowscount(selectallstudentcount);
        System.out.println("总条数为 ------ " + pageCountUtil.getRowscount());


        int pagerowscount = 5;//设置每页显示5条数据
        pageCountUtil.setPagerowscount(pagerowscount);
        System.out.println("每页显示条数为 ------ " + pageCountUtil.getPagerowscount());

        pageCountUtil.pagecount();//计算总页数
        System.out.println("总页数为 ------ " + pageCountUtil.getPagecount());

        pageCountUtil.setPagenow(pagenow);//设置当前页
        System.out.println("当前页为 ------ " + pageCountUtil.getPagenow());

        pageCountUtil.startrows();
        System.out.println("开始行为 ------ " + pageCountUtil.getStartrows());

        //分页结果
        /*List<ByStudent> pagecount = byStudentService.pagecount(pageCountUtil.getStartrows(),pageCountUtil.getPagerowscount());*/
        List<ByStudent> pagecount2 = byStudentService.pagecount2(pageCountUtil.getStartrows(),pageCountUtil.getPagerowscount());

       /* Map<String,Integer> map = new HashMap<>();

        map.put("rowscount",13);*/

        System.out.println("分页结果长度为 ------ " + pagecount2.size());

        return pagecount2;

       /* List<ByStudent> selectallstudentinfo = byStudentService.selectallstudent();

        List<ByStudent> list = new ArrayList<>();

        list.addAll(selectallstudentinfo);

        for(int i = 0; i < list.size(); i++){

            System.out.println(list.get(i).getName() + " ------ " + list.get(i).getIdentity_number());

        }

        System.out.println();

        return selectallstudentinfo;*/

    }

    /**
     * 查询班级
     */
    @RequestMapping("student/studentclasses")
    @ResponseBody
    public List<ByStudent> studentclsses(){

        List<ByStudent> studentclsses = byStudentService.studentclsses();

        return studentclsses;

    }

    /**
     * 查询指定班级所有学生信息
     */
    @RequestMapping("/student/selectstudentinfobyclasses")
    @ResponseBody
    public List<ByCount> selectstudentinfobyclasses(String classes){

        List<ByCount> selectstudentinfobyclasses = byStudentService.selectstudentinfobyclasses(classes);

        List<ByCount> list = new ArrayList<>();

        list.addAll(selectstudentinfobyclasses);

        for(ByCount bc : list){

            System.out.println(bc.getName() + " ------ " + bc.getClasses());

        }

        return selectstudentinfobyclasses;

    }

    /**
     * 模糊查询学生详细信息
     */
    @RequestMapping("student/likestudentinfo")
    @ResponseBody
    public List<ByCount> likestudentinfo(String name, String value, ByCount byCount){


        System.out.println("查询身份为： " + name + " ---- 查询值为： " + value);

        List<ByCount> likestudentinfo = null;

        if(value.length() <= 4){

            byCount.setName(value);

            System.out.println("查询学生姓名为： " + byCount.getName());

            likestudentinfo = byStudentService.likestudentinfo(byCount);

            return likestudentinfo;

        }

            byCount.setIdentity_number(Integer.parseInt(value));

            System.out.println("查询学生学号为： " + byCount.getName());

            likestudentinfo = byStudentService.likestudentinfo(byCount);

        return likestudentinfo;

    }

    /**
     *Mybatis resulmap复杂查询
     */
    @RequestMapping("student/selectstudentcount")
    @ResponseBody
    public List<ByStudent> selectstudentcount(){

        List<ByStudent> selectstudentcount = byStudentService.selectstudentcount();

        return selectstudentcount;

    }

    /**
     *Mybatis resulmap复杂查询-关联嵌套查询
     */
    @RequestMapping("student/selectstudentcount2")
    @ResponseBody
    public List<ByStudent> selectstudentcount2(){

        List<ByStudent> selectstudentcount2 = byStudentService.selectstudentcount2();

        return selectstudentcount2;

    }

    /**
     *Mybatis resulmap manytooone
     */
    @RequestMapping("student/selectsc")
    @ResponseBody
    public List<ByStudent> selectsc(){

        List<ByStudent> selectsc = byStudentService.selectsc();

        return selectsc;

    }

    /**
     * pageHelper
     */
    @RequestMapping("student/countstudent")
    @ResponseBody
    public  PageInfo<ByStudent> countstudent(int pageNum,int pageSize){

        PageHelper.startPage(pageNum,pageSize);
        List<ByStudent> liststudent = byStudentService.countstudent();

        PageInfo<ByStudent> pageInfo = new PageInfo<>(liststudent);

        return pageInfo;
    }

}
