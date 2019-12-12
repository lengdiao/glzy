package com.ecard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ecard.entity.Department;
import com.ecard.entity.DrugStore;
import com.ecard.pojo.queryResult.DepartmentQr;
@Mapper
public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

	List<DepartmentQr> findDepartment(
			@Param("mechanismName") String mechanismName, 
			@Param("departmentName") String departmentName, 
			@Param("disableFlag") Integer disableFlag);

	Department selectByName(@Param("name") String name);

	Department selectByIdAndName(Department department);

	
}