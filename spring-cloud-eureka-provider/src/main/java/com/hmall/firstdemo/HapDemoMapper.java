package com.hmall.firstdemo;

import com.hmall.firstdemo.HapDemo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HapDemoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HapDemo record);

    int insertSelective(HapDemo record);

    HapDemo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HapDemo record);

    int updateByPrimaryKey(HapDemo record);
}