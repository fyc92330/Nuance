package org.chun.guns.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "database")
public class DatabaseRoleProperties {
	private int count;
	private String[] users;
	private String[] passwords;
}
