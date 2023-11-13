package org.chun.eyes.security.filter;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class VersionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		String version = servletRequest.getParameter("version");
		if(StringUtils.isBlank(version)){
			throw new RuntimeException("version is not exists.");
		}

		servletRequest.setAttribute("version", null);
		filterChain.doFilter(servletRequest, servletResponse);
	}
}
