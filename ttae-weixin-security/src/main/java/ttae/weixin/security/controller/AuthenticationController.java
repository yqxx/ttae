package ttae.weixin.security.controller;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import ttae.weixin.bean.Result;
import ttae.weixin.security.bean.Authentication;
import ttae.weixin.security.jwt.JwtAuthenticationRequest;
import ttae.weixin.security.jwt.JwtAuthenticationResponse;
import ttae.weixin.security.jwt.JwtTokenUtil;
import ttae.weixin.security.model.Principal;
import ttae.weixin.security.service.AuthenticationService;
import ttae.weixin.utils.ResultUtil;

@Slf4j
@RestController
@Api(tags="凭证接口")
public class AuthenticationController {

	@Autowired AuthenticationService authenticationService;
	@Autowired UserDetailsService userDetailsService;
	@Autowired JwtTokenUtil jwtTokenUtil;
	
	@GetMapping("/login")
	@ApiOperation(value = "获取接口调用凭证")
	public Result<?> createAuthenticationToken(JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
		try {
			final JwtAuthenticationResponse response = authenticationService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
			return ResultUtil.ok(response);
		} catch (BadCredentialsException e) {
			log.error("", e);
			return ResultUtil.error("账号密码有误");
		}
	}
	
	@GetMapping("/info")
	@ApiOperation(value = "获取凭证信息")
    public Result<?> getUserInfo(String token) throws AuthenticationException{
		try {
			String username = jwtTokenUtil.getUsernameFromToken(token);
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            Collection<? extends GrantedAuthority> userAuthorities = userDetails.getAuthorities();
            Set<String> roles = AuthorityUtils.authorityListToSet(userAuthorities);
            Authentication authentication = new Authentication();
            authentication.setRoles(roles);
            authentication.setUsername(username);
			return ResultUtil.ok(authentication);
		} catch (BadCredentialsException e) {
			return ResultUtil.error("");
		}
    }
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ApiOperation(value = "创建凭证账号")
	public Result<?> register(@RequestBody Principal principal) {
		return ResultUtil.ok(authenticationService.register(principal));
	}
}
