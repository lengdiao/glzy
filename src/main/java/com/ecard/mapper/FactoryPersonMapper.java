package com.ecard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ecard.entity.FactoryPerson;
import com.ecard.pojo.queryResult.FactoryPersonQr;
@Mapper
public interface FactoryPersonMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FactoryPerson record);

    int insertSelective(FactoryPerson record);

    FactoryPerson selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FactoryPerson record);

    int updateByPrimaryKey(FactoryPerson record);

	FactoryPerson selectByPhoneId(@Param("id") Long id, @Param("phone") String phone);

	List<FactoryPersonQr> selectByNamePhoneNo(
			@Param("name") String name, 
			@Param("phone") String phone, 
			@Param("mechanismNo") Long mechanismNo, 
			@Param("departmentNo") Long departmentNo);
}