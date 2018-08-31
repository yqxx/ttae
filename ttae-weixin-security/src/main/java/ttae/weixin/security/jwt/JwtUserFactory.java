package ttae.weixin.security.jwt;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import ttae.weixin.security.model.Principal;

public final class JwtUserFactory {

	private JwtUserFactory() {
		
	}

	public static JwtUser create(Principal user, List<GrantedAuthority> authorities) {
		return new JwtUser(String.valueOf(user.getId()), user.getUsername(), user.getPassword(), authorities);
	}

}
