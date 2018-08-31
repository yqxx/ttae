package ttae.weixin.security.model;

import java.security.PermissionCollection;

import org.springframework.security.core.GrantedAuthority;

public class GrantedPermissions implements GrantedAuthority {

	private static final long serialVersionUID = 2644761550361790919L;
	 
	private PermissionCollection permissions;

	public GrantedPermissions(PermissionCollection permissions) {
		this.permissions = permissions;
	}

	@Override
	public String getAuthority() {
		return "_permissions";
	}

	public PermissionCollection getPermissions() {
		return permissions;
	}

	@Override
	public String toString() {
		return new StringBuffer("GrantedPermissions[").append(permissions).append("]").toString();
	}

}