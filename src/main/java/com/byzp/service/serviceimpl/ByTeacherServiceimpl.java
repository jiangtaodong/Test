package com.byzp.service.serviceimpl;

import com.byzp.mapper.ByTeacherMapper;
import com.byzp.pojo.ByStudent;
import com.byzp.pojo.ByTeacher;
import com.byzp.service.ByTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ByTeacherServiceimpl implements ByTeacherService {

    @Autowired
    ByTeacherMapper byTeacherMapper;

    @Override
    public int insertteacherinfo(ByStudent byStudent) {

        int insertteacherinfo = byTeacherMapper.insertteacherinfo(byStudent);

        return insertteacherinfo;
    }

    @Override
    public List<ByTeacher> liketeacherinfo(ByTeacher byTeacher) {

        //查询by_teacher表所有数据
        List<ByTeacher> liketeacherinfo = byTeacherMapper.liketeacherinfo(byTeacher);

        return liketeacherinfo;
    }

    @Override
    public List<ByTeacher> importbyteacherdata() {

        List<ByTeacher> importbyteacherdata = byTeacherMapper.importbyteacherdata();

        for(ByTeacher solrlist : importbyteacherdata){



        }

        return importbyteacherdata;
    }
}
