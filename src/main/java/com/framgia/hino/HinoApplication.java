package com.framgia.hino;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.framgia.hino.filter.TrollFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@RestController
public class HinoApplication extends SpringBootServletInitializer {

	@Value("${hino.welcome:Fuck ya!}")
	private String welcome;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HinoApplication.class);
	}

	public static void main(String[] args) {
		log.info("INFO");
		log.debug("DEBUG");
		log.warn("WARN");
		SpringApplication.run(HinoApplication.class, args);
	}

	@RequestMapping("/")
	public String xxx() {
		return welcome;
	}

	/**
	 * they said put this inside
	 * my @{@link org.springframework.context.annotation.Configuration
	 * Configuration}, so i put it here :)
	 * 
	 * @return FilterRegistrationBean
	 */
	@Bean
	public FilterRegistrationBean<TrollFilter> trollFilterRegistrationBean() {
		FilterRegistrationBean<TrollFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new TrollFilter());
		registrationBean.addUrlPatterns("/champions/");
		return registrationBean;
	}
}
