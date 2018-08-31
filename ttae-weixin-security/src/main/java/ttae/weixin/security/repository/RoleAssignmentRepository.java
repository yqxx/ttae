package ttae.weixin.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ttae.weixin.security.model.RoleAssignment;

public interface RoleAssignmentRepository extends JpaRepository<RoleAssignment, Long> {

}
