package org.chun.guns.dao;

import org.chun.guns.entity.SysDatabaseInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SysDatabaseInfoMapper implements RowMapper<SysDatabaseInfo> {
	public static SysDatabaseInfo mapper(ResultSet rs, int rowNum) throws SQLException {
		return new SysDatabaseInfoMapper().mapRow(rs, rowNum);
	}

	@Override
	public SysDatabaseInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		SysDatabaseInfo info = new SysDatabaseInfo();
		info.setDbNum(rs.getLong("db_num"));
		info.setDbName(rs.getString("db_name"));
		info.setDbUsername(rs.getString("db_username"));
		info.setDbPassword(rs.getString("db_password"));
		info.setDbDriverType(rs.getString("db_driver_type"));
		info.setDbUrl(rs.getString("db_url"));
		info.setDbPort(rs.getString("db_port"));
		return info;
	}

}
