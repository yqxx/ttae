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
import ttae.weixin.security.model.SecurityId;
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
			return JwtUserFactory.create(user, findGrantedAuthorities(user.getUsername()));
		}
	}
	
	protected List<GrantedAuthority> findGrantedAuthorities(String username) {
		List<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();

		List<Role> roles = findAssignedRolesOfSid(username, SecurityId.Type.PRINCIPAL);
		for (Role role : roles) {
			gas.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		}
		gas.add(new GrantedPermissions(findPermissionsOfUser(username, roles)));
		return gas;
	}

	protected List<Role> findAssignedRolesOfSid(String sid, SecurityId.Type type) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select r.* from sec_role_assignment ra, sec_role r, sec_sid s ");
		sb.append(" where ra.role_id = r.id and ra.sid_id = s.id and s.sid = ? and s.type = ? ");

		return jdbcTemplate.query(sb.toString(), new Object[] { sid, type },
				new BeanPropertyRowMapper<Role>(Role.class));
	}

	protected PermissionCollection findPermissionsOfUser(String username, List<Role> roles) {
		List<PermissionVo> permissionVo = new ArrayList<PermissionVo>();
		for (Role role : roles) {
			StringBuffer sb = new StringBuffer();
			sb.append(" select pa.mask, p.id , p.actions, p.`code`, ");
			sb.append(" p.description, p.`name` ");
			sb.append(" from sec_permission_assignment pa, sec_permission p, sec_sid s ");
			sb.append(" where pa.permission_id = p.id and pa.sid_id = s.id and s.sid = ? and s.type = ? ");

			List<PermissionVo> pas = jdbcTemplate.query(sb.toString(),
					new Object[] { role.getId(), 1 },
					new BeanPropertyRowMapper<PermissionVo>(PermissionVo.class));
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
