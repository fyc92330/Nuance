package org.chun.guns.dao;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

@Component
public class SysDatabaseInfoSqlBuilder {
	private final String table = "sys_database_info";
	private String[] columns = {
		"db_num",
		"db_name",
		"db_username",
		"db_password",
		"db_url",
		"db_driver_type",
		"db_port"
	};

	public String getAll() {
		return new SQL()
			.SELECT(columns)
			.FROM(table)
			.toString();
	}

	public String getById(){
		return new SQL()
			.SELECT(columns)
			.FROM(table)
			.WHERE("db_num = #{dbNum}")
			.toString();
	}
}
