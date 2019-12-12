package com.ecard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ecard.entity.DrugStorePerson;
import com.ecard.entity.PtInfo;
import com.ecard.pojo.queryResult.DrugStorePersonQr;
@Mapper
public interface DrugStorePersonMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DrugStorePerson record);

    int insertSelective(DrugStorePerson record);

    DrugStorePerson selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DrugStorePerson record);

    int updateByPrimaryKey(DrugStorePerson record);

	List<DrugStorePersonQr> selectByNamePhoneNo(
			@Param("name") String name, 
			@Param("phone") String phone, 
			@Param("drugStoreNo") Long drugStoreNo);

	
}