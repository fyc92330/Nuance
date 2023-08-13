package org.chun.aop.validator;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum RoleType {

	ADMIN("1"),
	MANAGER("2"),
	ROLE("3"),
	USER("4");

	private final String type;

	public static RoleType getInstance(String type) {
		return Arrays.stream(values())
				.filter(e -> e.type.equals(type))
				.findAny()
				.orElseThrow(() -> new EnumConstantNotPresentException(RoleType.class, type));
	}
}
