package ttae.weixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class TtaeWeixinServiceConfiguration {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(TtaeWeixinServiceConfiguration.class, args);
	}
}
