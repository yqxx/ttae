package ttae.weixin.security.utils;

import java.security.PermissionCollection;
import java.util.Collection;

import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import ttae.weixin.security.model.GrantedPermissions;

public class SecurityUtils {

	public static PermissionCollection getPermissions(Authentication authentication) {
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		PermissionCollection permissions = null;
		for (GrantedAuthority ca : authorities) {
			if (ca instanceof GrantedPermissions) {
				permissions = ((GrantedPermissions) ca).getPermissions();
				break;
			}
		}
		return permissions;
	}

	private static AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

	public String getUserName() {
		Authentication auth = getAuthentication();
		return auth.getName();
	}

	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public static Object getPrincipal() {
		return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public static boolean isAuthenticated() {
		Authentication auth = getAuthentication();
		return !trustResolver.isAnonymous(auth);
	}
}
