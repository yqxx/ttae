package ttae.weixin.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ttae.weixin.security.model.Principal;

public interface PrincipalRepository extends JpaRepository<Principal, Long> {

	Principal findByUsername(String username);
}
