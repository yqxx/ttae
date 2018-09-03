package ttae.weixin.security.service;

import java.util.List;

import ttae.weixin.security.criteria.PermissionCriteria;
import ttae.weixin.security.model.Permission;

public interface PermissionService {

	List<Permission> findAll(PermissionCriteria criteria);
	
	Permission getOne(Long id);
	
	void save(Permission permission);
	
	void delete(Long id);
}
