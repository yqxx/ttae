package ttae.mp.restful.controller;

import java.util.Collection;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ttae.weixin.bean.TtaeUser;
import ttae.weixin.http.ResponseEntity;
import ttae.weixin.security.jwt.JwtAuthenticationRequest;
import ttae.weixin.security.jwt.JwtAuthenticationResponse;
import ttae.weixin.security.jwt.JwtTokenUtil;
import ttae.weixin.security.model.Principal;
import ttae.weixin.security.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired AuthService authService;
	@Autowired private JwtTokenUtil jwtTokenUtil;
	@Autowired private UserDetailsService userDetailsService;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
		try {
			final JwtAuthenticationResponse response = authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
			return ResponseEntity.ok(response);
		} catch (BadCredentialsException e) {
			log.error("", e);
			return ResponseEntity.error("账号密码有误");
		}
	}
	
	@GetMapping("/info")
	@ApiOperation(value = "通过token获取")
    public ResponseEntity<?> getUserInfo(String token) throws AuthenticationException{
		try {
			String username = jwtTokenUtil.getUsernameFromToken(token);
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            Collection<? extends GrantedAuthority> userAuthorities = userDetails.getAuthorities();
            Set<String> roles = AuthorityUtils.authorityListToSet(userAuthorities);
            TtaeUser user = new TtaeUser();
            user.setRoles(roles);
            user.setUsername(username);
			return ResponseEntity.ok(userDetails);
		} catch (BadCredentialsException e) {
			return ResponseEntity.error();
		}
    }
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Principal register(@RequestBody Principal principal) {
		return authService.register(principal);
	}
}
