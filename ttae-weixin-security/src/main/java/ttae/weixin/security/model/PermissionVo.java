package ttae.weixin.security.model;

import lombok.Data;

@Data
public class PermissionVo {

	private String code;
	
	private String name;
	
	private int mask;
}
