package com.itwillbs.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomLoginSuccessHandler.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		logger.warn("Login Success");

		List<String> roleNames = new ArrayList<>();

		auth.getAuthorities().forEach(authority -> {
			roleNames.add(authority.getAuthority());
		});

		logger.warn("ROLE NAMES: " + roleNames);

		String path = "";

		if (roleNames.contains("ROLE_ADMIN")) {
			logger.warn("๊ด?๋ฆฌ์ ??ด? ๋ก๊ทธ?ธ");

			path = "/main";
		}

		if (roleNames.contains("ROLE_MANAGER")) {
			logger.warn("๋งค๋?? ??ด? ๋ก๊ทธ?ธ");

			path = "/main";
		}

		if (roleNames.contains("ROLE_MEMBER")) {
			logger.warn("?ฌ?ฉ? ??ด? ๋ก๊ทธ?ธ");

			path = "/main";
		}

		response.sendRedirect(path);
	}

}
