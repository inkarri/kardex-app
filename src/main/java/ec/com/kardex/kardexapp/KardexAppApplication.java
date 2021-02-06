package ec.com.kardex.kardexapp;

import ec.com.kardex.client.config.AppConfig;
import ec.com.kardex.services.config.WebAppInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = "ec.com.kardex")
@Import({AppConfig.class,
        WebAppInitializer.class})
public class KardexAppApplication extends SpringBootServletInitializer implements
        WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(KardexAppApplication.class, args);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }

}
