package com.framgia.hino.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;

@Order(1)
public class TrollFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("TrollFilter Pre-Processing: " + ((HttpServletRequest)request).getServletPath());
		chain.doFilter(request, response);
		// FIXME LOL won't work, maybe coz response has been committed? =))
		((HttpServletResponse) response).setHeader("Troll-Filter", "Just kidding!");
		System.out.println("TrollFilter Post-Processing...");
	}
}
