package ttae.weixin.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttae.weixin.exception.TtaeException;
import ttae.weixin.security.criteria.RoleCriteria;
import ttae.weixin.security.model.Role;
import ttae.weixin.security.repository.RoleRepository;
import ttae.weixin.security.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired RoleRepository roleRepository;
	
	@Override
	public List<Role> findAll(RoleCriteria criteria) {
		return roleRepository.findAll();
	}

	@Override
	public Role getOne(Long id) {
		return roleRepository.getOne(id);
	}

	@Override
	public void save(Role role) {
		if (null == role.getId()) {
			if (null != roleRepository.getRoleByCode(role.getCode())) {
				throw new TtaeException(String.format("编码%s已存在", role.getCode()));
			}
		}
		roleRepository.save(role);
	}

	@Override
	public void delete(Long id) {
		roleRepository.deleteById(id);
	}

}
