package com.ecard.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ecard.entity.Sednsms;

@Mapper
public interface SednsmsMapper {
    
	void save(Sednsms sednsms);
	
	Sednsms selectByPhone(String phone);

	void update(Sednsms sednsms);
}
