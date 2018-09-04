package ttae.weixin.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="sec_permission")
public class Permission {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="actions")
	private String actions;
	
	@Column(name = "code")
	private String code;
	
	@Column(name="name")
	private String name;
}
