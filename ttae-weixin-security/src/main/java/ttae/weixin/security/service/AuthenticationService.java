package ttae.weixin.security.service;

import ttae.weixin.security.jwt.JwtAuthenticationResponse;
import ttae.weixin.security.model.Principal;

public interface AuthenticationService {

	Principal register(Principal principal);
	
	JwtAuthenticationResponse login(String username, String password);
}
