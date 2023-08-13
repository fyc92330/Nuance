package org.chun.aop.validator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Role {

	private Long roleNum;
	private String roleName;
	private String roleType;
	private String roleDesc;

	public Role(Long num, String type) {
		this.roleNum = num;
		this.roleType = type;
	}
}
