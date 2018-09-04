package ttae.weixin.security.service;

import java.util.List;

import ttae.weixin.security.criteria.RoleCriteria;
import ttae.weixin.security.model.Role;

public interface RoleService {

	List<Role> findAll(RoleCriteria criteria);
	
	Role getOne(Long id);
	
	void save(Role role);
	
	void delete(Long id);
}
