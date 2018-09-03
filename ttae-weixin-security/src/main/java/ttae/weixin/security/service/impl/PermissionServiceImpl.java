package ttae.weixin.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttae.weixin.security.criteria.PermissionCriteria;
import ttae.weixin.security.model.Permission;
import ttae.weixin.security.repository.PermissionRepository;
import ttae.weixin.security.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired PermissionRepository repository;
	
	@Override
	public List<Permission> findAll(PermissionCriteria criteria) {
		return repository.findAll();
	}

	@Override
	public Permission getOne(Long id) {
		return repository.getOne(id);
	}

	@Override
	public void save(Permission permission) {
		repository.save(permission);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
