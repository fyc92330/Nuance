package org.chun.guns.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DemoUserDAO {

	List<DemoUser> getAll();

	DemoUser getById(@Param("userNum") Long userNum);
}
