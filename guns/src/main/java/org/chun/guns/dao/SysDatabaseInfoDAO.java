package org.chun.guns.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.chun.guns.entity.SysDatabaseInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysDatabaseInfoDAO {

	/**
	 * @see SysDatabaseInfoSqlBuilder#getAll
	 */
	@SelectProvider(type = SysDatabaseInfoSqlBuilder.class, method = "getAll")
	List<SysDatabaseInfo> getAll();

	/**
	 * @see SysDatabaseInfoSqlBuilder#getById
	 */
	@SelectProvider(type = SysDatabaseInfoSqlBuilder.class, method = "getById")
	SysDatabaseInfo getById(@Param("dbNum") Long dbNum);
}
