package com.byzp.service;

import com.byzp.pojo.ByStudent;
import com.byzp.pojo.ByTeacher;

import java.util.List;

public interface ByTeacherService {

    int insertteacherinfo(ByStudent byStudent);

    List<ByTeacher> liketeacherinfo(ByTeacher byTeacher);

    List<ByTeacher> importbyteacherdata();

}
