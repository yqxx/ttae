package ttae.weixin.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ttae.weixin.security.model.PrincipalRole;

public interface PrincipalRoleRepository extends JpaRepository<PrincipalRole, Long> {

}
