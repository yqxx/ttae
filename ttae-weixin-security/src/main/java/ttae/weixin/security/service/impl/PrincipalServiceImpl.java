package ttae.weixin.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttae.weixin.exception.TtaeException;
import ttae.weixin.security.criteria.PrincipalCriteria;
import ttae.weixin.security.model.Principal;
import ttae.weixin.security.repository.PrincipalRepository;
import ttae.weixin.security.service.PrincipalService;

@Service
public class PrincipalServiceImpl implements PrincipalService {

	@Autowired PrincipalRepository principalRepository;
	
	@Override
	public List<Principal> findAll(PrincipalCriteria criteria) {
		return principalRepository.findAll();
	}

	@Override
	public Principal getOne(Long id) {
		return principalRepository.getOne(id);
	}

	@Override
	public void save(Principal principal) {
		principalRepository.save(principal);
	}

	@Override
	public void delete(Long id) {
		principalRepository.deleteById(id);
	}

}
