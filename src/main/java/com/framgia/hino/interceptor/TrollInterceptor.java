package com.framgia.hino.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TrollInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		System.out.println("Pre Handle method is Calling");
		response.setHeader("Hino", "preHandle!");
		// System.out.println(request.getHeaderNames());
		return HandlerInterceptor.super.preHandle(request, response, handler); // FIXME do nothing
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		System.out.println("Post Handle method is Calling");
		response.setHeader("Hino", "postHandle!");
		// response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		// modelAndView.setStatus(HttpStatus.CONFLICT);
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView); // FIXME do nothing
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		System.out.println("Request and Response is completed");
		response.setHeader("Hino", "afterCompletion!");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex); // FIXME do nothing
	}

}
