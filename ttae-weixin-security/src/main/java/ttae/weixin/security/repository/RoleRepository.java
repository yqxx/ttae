package ttae.weixin.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ttae.weixin.security.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	public Role getRoleByCode(String code);
}
