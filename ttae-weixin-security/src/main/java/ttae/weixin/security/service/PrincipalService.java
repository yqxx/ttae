package ttae.weixin.security.service;

import java.util.List;

import ttae.weixin.security.criteria.PrincipalCriteria;
import ttae.weixin.security.model.Principal;

public interface PrincipalService {

	List<Principal> findAll(PrincipalCriteria criteria);
	
	Principal getOne(Long id);
	
	void save(Principal principal);
	
	void delete(Long id);
}
