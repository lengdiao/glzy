package com.ecard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ecard.entity.CloudPassInfo;
import com.ecard.entity.Mechanism;
import com.ecard.pojo.queryResult.CloudPassInfoQr;
@Mapper
public interface CloudPassInfoMapper {
    int deleteByPrimaryKey(Long cloudPassNo);

    int insert(CloudPassInfo record);

    int insertSelective(CloudPassInfo record);

    CloudPassInfo selectByPrimaryKey(Long cloudPassNo);

    int updateByPrimaryKeySelective(CloudPassInfo record);

    int updateByPrimaryKey(CloudPassInfo record);
    
    CloudPassInfo selectByPhone(String phone);

	List<CloudPassInfoQr> findUser(
			@Param("phone") String phone, 
			@Param("name") String name, 
			@Param("departName") String departName);

	CloudPassInfo selectByNameAndIdCard(
			@Param("name") String name, 
			@Param("idCard") String idCard);

	CloudPassInfo selectByPhoneAndStoreNo(
			@Param("contactPhone") String contactPhone, 
			@Param("drugStoreNo") Long drugStoreNo);

	CloudPassInfo selectByPhoneAndPassNo(
			@Param("phone") String phone, 
			@Param("cloudPassNo") Long cloudPassNo);

	CloudPassInfo selectByPhoneFlag(String phone);

	CloudPassInfo selectByNameAndIdCard1(
			@Param("name") String name, 
			@Param("idCard") String idCard);
	
}