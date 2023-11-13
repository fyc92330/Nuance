package org.chun.eyes.security;

import jakarta.servlet.Filter;
import org.chun.eyes.security.filter.VersionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean<Filter> versionFilterRegistrationBean(VersionFilter versionFilter) {
		FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>(versionFilter);
		registrationBean.addUrlPatterns("/admin/*");
		registrationBean.setOrder(1);
		registrationBean.setName("VersionFilter");
		return registrationBean;
	}


}
