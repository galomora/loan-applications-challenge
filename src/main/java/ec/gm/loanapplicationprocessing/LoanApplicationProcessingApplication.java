package ec.gm.loanapplicationprocessing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication

public class LoanApplicationProcessingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanApplicationProcessingApplication.class, args);
	}


}
