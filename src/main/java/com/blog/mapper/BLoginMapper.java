package com.blog.mapper;

import com.blog.pojo.BLogin;

public interface BLoginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BLogin record);

    int insertSelective(BLogin record);

    BLogin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BLogin record);

    int updateByPrimaryKey(BLogin record);
}