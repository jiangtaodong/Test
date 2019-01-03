package com.blog.mapper;

import com.blog.pojo.BBlogInfo;

public interface BBlogInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BBlogInfo record);

    int insertSelective(BBlogInfo record);

    BBlogInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BBlogInfo record);

    int updateByPrimaryKeyWithBLOBs(BBlogInfo record);

    int updateByPrimaryKey(BBlogInfo record);
}