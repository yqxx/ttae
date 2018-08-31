package ttae.mp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import ttae.weixin.TtaeWeixinServiceConfiguration;
import ttae.weixin.security.TtaeSecurityConfiguration;

@SpringBootApplication
@Import({TtaeSecurityConfiguration.class, TtaeWeixinServiceConfiguration.class})
public class TtaeMpRestfulApplication {

	public static void main(String[] args) {
		SpringApplication.run(TtaeMpRestfulApplication.class, args);
	}
}
