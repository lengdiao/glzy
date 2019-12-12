package com.ecard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ecard.entity.DrugSet;
@Mapper
public interface DrugSetMapper {
    int deleteByPrimaryKey(Long drugNo);

    int insert(DrugSet record);

    int insertSelective(DrugSet record);

    DrugSet selectByPrimaryKey(Long drugNo);

    int updateByPrimaryKeySelective(DrugSet record);

    int updateByPrimaryKey(DrugSet record);

	Long selectMaxId();

	List<DrugSet> selectByNameAndFlag(
			@Param("drugName") String drugName, 
			@Param("disableFlag") Integer disableFlag);

}