package com.ecard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ecard.entity.DrugStore;
import com.ecard.pojo.queryResult.RoleListQr;
@Mapper
public interface DrugStoreMapper {
    int deleteByPrimaryKey(Long drugStoreNo);

    int insert(DrugStore record);

    int insertSelective(DrugStore record);

    DrugStore selectByPrimaryKey(Long drugStoreNo);

    int updateByPrimaryKeySelective(DrugStore record);

    int updateByPrimaryKey(DrugStore record);

	DrugStore selectByCloudPassNo(Long cloudPassNo);

	List<DrugStore> findDrugStore(
			@Param("drugStoreName") String drugStoreName, 
			@Param("disableFlag") Integer disableFlag);

	DrugStore selectByPhone(@Param("phone") String phone);

}