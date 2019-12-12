package com.ecard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ecard.entity.MallOrder;
import com.ecard.pojo.queryResult.MallOrderQr;
@Mapper
public interface MallOrderMapper {
    int deleteByPrimaryKey(Long mallNo);

    int insert(MallOrder record);

    int insertSelective(MallOrder record);

    MallOrder selectByPrimaryKey(Long mallNo);

    int updateByPrimaryKeySelective(MallOrder record);

    int updateByPrimaryKey(MallOrder record);

	List<MallOrderQr> findMallOrder(
			@Param("mallNo") Long mallNo, 
			@Param("startTime") String startTime, 
			@Param("stopTime") String stopTime, 
			@Param("checkStatus") Integer checkStatus,
			@Param("shippingStatus") Integer shippingStatus,
			@Param("orderStatus") Integer orderStatus);

	Long generateMallno();

	List<MallOrderQr> findMallOrderByCode(
			@Param("phone") String phone, 
			@Param("name") String name);

	List<MallOrderQr> findMallOrderByCloudNo(Long cloudPassNo);
}