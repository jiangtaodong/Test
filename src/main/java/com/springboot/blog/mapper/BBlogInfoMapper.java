package com.springboot.blog.mapper;

import com.springboot.blog.pojo.BBlogInfo;

public interface BBlogInfoMapper {
    int insert(BBlogInfo record);

    int insertSelective(BBlogInfo record);
}