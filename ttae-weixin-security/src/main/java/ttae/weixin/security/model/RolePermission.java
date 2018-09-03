package ttae.weixin.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="sec_role_permission")
public class RolePermission {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="mask")
	private int mask;
	
	@Column(name="role_id")
	private Long roleId;
	
	@Column(name="permission_id")
	private Long permissionId;
}
