package ttae.weixin.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import ttae.weixin.security.access.PermissionVoter;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class TtaeSecurityConfiguration {

	@Bean
	public AffirmativeBased AffirmativeBased(){
		List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<>();
		PermissionVoter permissionVoter = new PermissionVoter();
		decisionVoters.add(permissionVoter);
		
		AffirmativeBased AffirmativeBased = new AffirmativeBased(decisionVoters);
		return AffirmativeBased;
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(TtaeSecurityConfiguration.class, args);
	}
}
