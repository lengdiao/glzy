package com.ecard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ecard.entity.Authority;
import com.ecard.pojo.queryResult.Authoritys;
@Mapper
public interface AuthorityMapper {
    int deleteByPrimaryKey(Long authorityNo);

    int insert(Authority record);

    int insertSelective(Authority record);

    Authority selectByPrimaryKey(Long authorityNo);

    int updateByPrimaryKeySelective(Authority record);

    int updateByPrimaryKey(Authority record);

	List<Authoritys> selectByRoleno(int roleNo);

	List<Authority> selectAllauths();

	List<Authority> selectByNo(String authorityNo);
}