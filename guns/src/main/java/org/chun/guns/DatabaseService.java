package org.chun.guns;

import lombok.extern.slf4j.Slf4j;
import org.chun.guns.common.DatabaseDriverType;
import org.chun.guns.common.SysDatabaseInfoDAO;
import org.chun.guns.common.SysDatabaseInfoMapper;
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
					String sql = "SELECT * FROM SYS_DATABASE_INFO WHERE DB_NUM = ?";
					return entry.getValue().queryForObject(sql, new Object[]{key}, SysDatabaseInfoMapper::mapper);
				})
				.forEach(info -> log.info("SYS_DATABASE_INFO: {}", info));
	}

}
