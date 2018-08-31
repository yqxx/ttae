package ttae.weixin.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sec_sid")
public class SecurityId {

	public static enum Type {
		PRINCIPAL,
		ROLE
	}
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="sid")
	private String sid;
	
	@Column(name="type")
	private Type type = Type.PRINCIPAL;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
