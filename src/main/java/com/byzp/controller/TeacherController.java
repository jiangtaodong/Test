package com.byzp.controller;

import com.byzp.pojo.ByTeacher;
import com.byzp.service.ByLoginService;
import com.byzp.service.ByStudentService;
import com.byzp.service.ByTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    private ByLoginService byLoginService;

    @Autowired
    private ByStudentService byStudentService;

    @Autowired
    private ByTeacherService byTeacherService;

    /**
     * 模糊查询老师详细信息
     */
    @RequestMapping("teacher/liketeacherinfo")
    @ResponseBody
    public List<ByTeacher> liketeacherinfo(String name, String value, ByTeacher byTeacher){

        System.out.println("查询身份为： " + name + " ---- 查询值为： " + value);

        List<ByTeacher> liketeacherinfo = null;

        if(value.length() <= 4){

            byTeacher.setTname(value);

            System.out.println("查询老师姓名为： " + byTeacher.getTname());

            liketeacherinfo = byTeacherService.liketeacherinfo(byTeacher);

            return liketeacherinfo;

        }

        byTeacher.setTnumber(Integer.parseInt(value));

        System.out.println("查询老师工号为： " + byTeacher.getTnumber());

        liketeacherinfo = byTeacherService.liketeacherinfo(byTeacher);

        return liketeacherinfo;

    }

    @RequestMapping("importbyteacherdata")
    @ResponseBody
    public List<ByTeacher> importbyteacherdata(){

        List<ByTeacher> importbyteacherdata = byTeacherService.importbyteacherdata();

        return importbyteacherdata;

    }

}
