package ttae.weixin.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="sec_principal_role")
public class PrincipalRole {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="principal_id")
	private Long principalId;
	
	@Column(name="role_id")
	private Long roleId;
}
