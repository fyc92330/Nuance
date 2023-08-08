package org.chun.guns;

import lombok.extern.slf4j.Slf4j;

import org.chun.guns.entity.SysDatabaseInfo;
import org.chun.guns.enums.DatabaseDriverType;
import org.chun.guns.dao.SysDatabaseInfoDAO;
import org.chun.guns.dao.SysDatabaseInfoMapper;
import org.chun.guns.dao.SysDatabaseInfoSqlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class DatabaseService {

	private static final String DATABASE_URL_PATTERN = "jdbc:{dbDriverType}://{dbUrl}:{dbPort}/{dbName}";

	@Autowired
	private SysDatabaseInfoDAO sysDatabaseInfoDAO;
	@Autowired
	private SysDatabaseInfoSqlBuilder sysDatabaseInfoSqlBuilder;

	@Autowired
	@Qualifier(value = "dataSourceMap")
	private Map<String, JdbcTemplate> dataSourceMap;

	public void initialize() {
		AtomicInteger integer = new AtomicInteger(1);
		sysDatabaseInfoDAO.getAll().stream()
				.map(info -> {
					DatabaseDriverType driverType = DatabaseDriverType.getInstance(info.getDbDriverType());
					String url = DATABASE_URL_PATTERN
							.replace("{dbDriverType}", driverType.getSymbol())
							.replace("{dbUrl}", info.getDbUrl())
							.replace("{dbPort}", info.getDbPort())
							.replace("{dbName}", info.getDbName());

					DriverManagerDataSource dataSource = new DriverManagerDataSource();
					dataSource.setDriverClassName(driverType.getDriverName());
					dataSource.setUsername(info.getDbUsername());
					dataSource.setPassword(info.getDbPassword());
					dataSource.setUrl(url);
					return new JdbcTemplate(dataSource);
				})
				.forEach(template -> dataSourceMap.put(String.valueOf(integer.getAndAdd(1)), template));
		log.info("DataSource Initialize Finish, map:{}", dataSourceMap);
	}

	public void getEachData() {
		dataSourceMap.entrySet().stream()
				.map(entry -> {
					long key = Long.parseLong(entry.getKey());
					String sql = sysDatabaseInfoSqlBuilder.getById();
					Map<String, Object> paramMap = Map.of("dbNum", key);
					return JdbcFormatUtil.singleValue(sql, SysDatabaseInfoMapper::mapper, paramMap, entry.getValue());
				})
				.forEach(info -> log.info("SYS_DATABASE_INFO: {}", info));
	}

}
