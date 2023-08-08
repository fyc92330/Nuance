package org.chun.guns;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JdbcFormatUtil {

	private static final Pattern PARAM_PATTERN = Pattern.compile("#\\{([^}]+)\\}");
	private static final String PLACEHOLDER_SYMBOL = "?";

	public static <T> T singleValue(String sql, RowMapper<T> mapper, Map<String, Object> paramMap, JdbcTemplate template){
		List<Object> params = new LinkedList<>();
		Matcher matcher = PARAM_PATTERN.matcher(sql);
		while(matcher.find()){
			String valueField = matcher.group(1);
			Object value = paramMap.get(valueField);
			if(value == null) throw new RuntimeException(String.format("Param is not exists. (%s)", valueField));
			sql = sql.replace(matcher.group(0), PLACEHOLDER_SYMBOL);
			params.add(value);
		}

		return template.queryForObject(sql, mapper, params.toArray());
	}

}
