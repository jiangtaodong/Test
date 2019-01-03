package com.byzp.mapper;

import com.byzp.pojo.ByStudent;
import com.byzp.pojo.ByTeacher;

import java.util.List;

public interface ByTeacherMapper {

    int insertteacherinfo(ByStudent byStudent);

    List<ByTeacher> liketeacherinfo(ByTeacher byTeacher);

    List<ByTeacher> importbyteacherdata();

}