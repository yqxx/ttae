package ttae.weixin.security.jwt;

import java.security.PermissionCollection;
import java.security.Permissions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ttae.weixin.security.model.GrantedPermissions;
import ttae.weixin.security.model.MaskPermission;
import ttae.weixin.security.model.PermissionVo;
import ttae.weixin.security.model.Principal;
import ttae.weixin.security.model.Role;
import ttae.weixin.security.repository.PrincipalRepository;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	PrincipalRepository principalRepository;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Principal user = principalRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {
			return JwtUserFactory.create(user, findGrantedAuthorities(user.getId()));
		}
	}
	
	protected List<GrantedAuthority> findGrantedAuthorities(Long principalId) {
		List<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();

		List<Role> roles = findRolesOfPrincipalId(principalId);
		for (Role role : roles) {
			gas.add(new SimpleGrantedAuthority("ROLE_" + role.getCode()));
		}
		gas.add(new GrantedPermissions(findPermissionsOfUser(roles)));
		return gas;
	}

	protected List<Role> findRolesOfPrincipalId(Long principalId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT r.* FROM sec_principal_role pr, sec_role r WHERE pr.principal_id=? AND pr.role_id=r.id ");
		return jdbcTemplate.query(sb.toString(), new Object[] { principalId }, new BeanPropertyRowMapper<Role>(Role.class));
	}

	protected PermissionCollection findPermissionsOfUser(List<Role> roles) {
		List<PermissionVo> permissionVo = new ArrayList<PermissionVo>();
		for (Role role : roles) {
			StringBuffer sb = new StringBuffer();
			sb.append(" SELECT rp.mask, p.`code` FROM sec_role_permission rp, sec_role r, sec_permission p WHERE rp.role_id=r.id AND rp.permission_id=p.id AND r.id=? ");
			List<PermissionVo> pas = jdbcTemplate.query(sb.toString(), new Object[] { role.getId() }, new BeanPropertyRowMapper<PermissionVo>(PermissionVo.class));
			permissionVo.addAll(pas);
		}
		Map<String, MaskPermission> permMap = new HashMap<String, MaskPermission>();
		for (PermissionVo pv : permissionVo) {
			MaskPermission p = permMap.get(pv.getCode());
			if (p == null) {
				permMap.put(pv.getCode(), new MaskPermission(pv.getCode(), pv.getMask()));
			} else {
				p.merge(pv.getMask());
			}
		}
		Permissions ps = new Permissions();
		for (MaskPermission p : permMap.values()) {
			ps.add(p);
		}
		return ps;
	}
}
