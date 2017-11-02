package com.lanou.mapper;

import com.lanou.bean.Message;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer mesId);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer mesId);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
}