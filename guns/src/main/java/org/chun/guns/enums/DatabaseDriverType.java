package org.chun.guns.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum DatabaseDriverType {
	POSTGRES("1", "postgresql", "org.postgresql.Driver"),
	MYSQL("2", "mysql", ""),
	MSSQL("3", "mssql", ""),
	ORACLE("4", "oracle", "");

	private final String type;
	private final String symbol;
	private final String driverName;

	public static DatabaseDriverType getInstance(String type) {
		return Arrays.stream(values())
				.filter(e -> e.type.equals(type))
				.findAny()
				.orElseThrow(() -> new EnumConstantNotPresentException(DatabaseDriverType.class, type));
	}
}
