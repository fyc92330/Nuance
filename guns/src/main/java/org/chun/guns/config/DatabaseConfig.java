package org.chun.guns.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties({DataSourceProperties.class, DatabaseRoleProperties.class})
public class DatabaseConfig {

	private final DataSourceProperties dataSourceProperties;
	private final DatabaseRoleProperties databaseRoleProperties;

	@Bean(name = "mainDataSource")
	@Primary
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
		dataSource.setUrl(dataSourceProperties.getUrl());
		dataSource.setUsername(dataSourceProperties.getUsername());
		dataSource.setPassword(dataSourceProperties.getPassword());
		return dataSource;
	}

	@Bean(name = "listDataSource")
	public DriverManagerDataSource dataSourceGun(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
		dataSource.setUrl(dataSourceProperties.getUrl());
		return dataSource;
	}

	@Bean(name = "roleMap")
	public Map<String, String> roleMap(){
		Map<String, String> roleMap = new HashMap<>();
		for(int i = 0; i < databaseRoleProperties.getCount(); i++){
			roleMap.put(databaseRoleProperties.getUsers()[i], databaseRoleProperties.getPasswords()[i]);
		}
		return roleMap;
	}
}
