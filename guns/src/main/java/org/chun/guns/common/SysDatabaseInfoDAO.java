package org.chun.guns.common;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysDatabaseInfoDAO {
	List<SysDatabaseInfo> getAll();

	SysDatabaseInfo getById(@Param("dbNum") Long dbNum);
}
