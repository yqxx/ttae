package ttae.weixin.security.support;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import ttae.weixin.security.jwt.JwtUser;

public class TtaeSecurityUtils {

	public static JwtUser getAuthentication() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof JwtUser) {
			return (JwtUser) authentication;
		}
		return null;
	}
	
	
}
