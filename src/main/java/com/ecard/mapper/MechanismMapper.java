package com.ecard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ecard.entity.Mechanism;
@Mapper
public interface MechanismMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Mechanism record);

    int insertSelective(Mechanism record);

    Mechanism selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Mechanism record);

    int updateByPrimaryKey(Mechanism record);

	List<Mechanism> findMechanism(@Param("mechanismName") String mechanismName);

	Mechanism selectByIdAndName(Mechanism mechanism);

}