package ttae.weixin.security.bean;

import java.util.Set;

import lombok.Data;

@Data
public class Authentication {

	private String username;
	
	private Set<String> roles;
}
