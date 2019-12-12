package com.ecard.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ecard.entity.PlatformPerson;
@Mapper
public interface PlatformPersonMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformPerson record);

    int insertSelective(PlatformPerson record);

    PlatformPerson selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformPerson record);

    int updateByPrimaryKey(PlatformPerson record);

	PlatformPerson selectByCloudPassNo(Long cloudPassNo);
}