package ttae.weixin.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ttae.weixin.security.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

	public Permission getPermissionByCode(String code);
}
