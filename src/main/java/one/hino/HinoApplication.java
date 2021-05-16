package one.hino;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@RestController
public class HinoApplication {

	@Value("${hino.welcome:Fuck ya!}")
	private String welcome;

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
}
