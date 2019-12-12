package com.ecard.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ecard.entity.PtOpen;
@Mapper
public interface PtOpenMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PtOpen record);

    int insertSelective(PtOpen record);

    PtOpen selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PtOpen record);

    int updateByPrimaryKey(PtOpen record);

	PtOpen findByPtOpenId(String openId);

	void deleteByPtNo(Long ptNo);

	PtOpen findByPtNo(Long ptNo);

}