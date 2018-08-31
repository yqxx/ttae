package ttae.weixin.security.access;

import java.security.PermissionCollection;
import java.util.Collection;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

import ttae.weixin.security.model.PermissionConfig;
import ttae.weixin.security.utils.SecurityUtils;

public class PermissionVoter implements AccessDecisionVoter<Object> {

	@Override
	public boolean supports(Class clazz) {
		return PermissionConfig.class.isAssignableFrom(clazz);
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return attribute instanceof PermissionConfig;
	}

	@Override
	public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
		PermissionCollection permissions = SecurityUtils.getPermissions(authentication);
		for (ConfigAttribute ca : attributes) {
			if (this.supports(ca)) {
				PermissionConfig permConfig = (PermissionConfig) ca;
				if (permissions != null && permissions.implies(permConfig.getPermission())) {
					return ACCESS_GRANTED;
				}
				return ACCESS_DENIED;
			}
		}
		return ACCESS_ABSTAIN;
	}

}
