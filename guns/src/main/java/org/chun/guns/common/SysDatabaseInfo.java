package org.chun.guns.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SysDatabaseInfo {
	private Long dbNum;
	private String dbName;
	private String dbUsername;
	private String dbPassword;
	private String dbUrl;
	private String dbDriverType;
	private String dbPort;
}
