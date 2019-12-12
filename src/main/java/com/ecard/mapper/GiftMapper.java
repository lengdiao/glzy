package com.ecard.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ecard.entity.Gift;
@Mapper
public interface GiftMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Gift record);

    int insertSelective(Gift record);

    Gift selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Gift record);

    int updateByPrimaryKey(Gift record);

	void updateByMallNo(
			@Param("mallNo") Long mallNo, 
			@Param("drugA") String drugA, 
			@Param("drugB") String drugB);
}