package ec.com.kardex.client.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"ec.com.kardex.client.dao"})
@EntityScan(basePackages = {"ec.com.kardex.client.dto"})
@ComponentScan(basePackages = {"ec.com.kardex"})
public class AppConfig {

}
