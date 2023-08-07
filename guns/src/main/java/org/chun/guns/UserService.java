package org.chun.guns;

import java.sql.ResultSet;
import java.util.concurrent.atomic.AtomicReference;

import javax.sql.DataSource;

import org.chun.guns.common.DemoUser;
import org.chun.guns.common.DemoUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
	private DemoUserDAO demoUserDAO;

	@Autowired
	@Qualifier(value = "listDataSource")
	private DriverManagerDataSource listDataSource;

	public DemoUser getUser(Long userNum) {
		return demoUserDAO.getById(userNum);
	}

	public DemoUser getUserByRole(String username, String password, Long userNum) {
		log.info("Second DB role ({}/{})", username, password);
		listDataSource.setUsername(username);
		listDataSource.setPassword(password);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(listDataSource);
		String sql = "SELECT * FROM DEMO_USER WHERE USER_NUM = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{userNum}, (resultSet, rowNum) -> {
			DemoUser demoUser = new DemoUser();
			demoUser.setUserNum(resultSet.getLong("USER_NUM"));
			demoUser.setUserName(resultSet.getString("USER_NAME"));
			demoUser.setUserGender(resultSet.getString("USER_GENDER"));

			log.info("user:{}", demoUser);
			return demoUser;
		});
	}

}
