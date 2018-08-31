package ttae.weixin.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ttae.weixin.security.jwt.JwtAuthenticationResponse;
import ttae.weixin.security.jwt.JwtTokenUtil;
import ttae.weixin.security.model.Principal;
import ttae.weixin.security.repository.PrincipalRepository;
import ttae.weixin.security.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired PrincipalRepository principalRepository;
	@Autowired AuthenticationManager authenticationManager;
	@Autowired UserDetailsService userDetailsService;
	@Autowired JwtTokenUtil jwtTokenUtil;
	
	 
	@Override
	public Principal register(Principal principal) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = principal.getPassword();
        principal.setPassword(encoder.encode(rawPassword));
        return principalRepository.save(principal);
	}


	@Override
	public JwtAuthenticationResponse login(String username, String password) {
		UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtTokenUtil.generateToken(userDetails);
	}

}
