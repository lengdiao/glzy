package com.ecard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ecard.entity.PtInfo;
import com.ecard.pojo.queryResult.PtInfoQr;
import com.ecard.pojo.queryResult.PtInfoQrr;
@Mapper
public interface PtInfoMapper {
    int deleteByPrimaryKey(Long ptNo);

    int insert(PtInfo record);

    int insertSelective(PtInfo record);

    PtInfo selectByPrimaryKey(Long ptNo);

    int updateByPrimaryKeySelective(PtInfo record);

    int updateByPrimaryKey(PtInfo record);

	List<PtInfoQr> findPtInfo(
			@Param("name") String name, 
			@Param("cloudPassNo") Long cloudPassNo, 
			@Param("idCard") String idCard,
			@Param("disableFlag") Integer disableFlag);

	PtInfo selectByCloudPassNo(Long cloudPassNo);
}