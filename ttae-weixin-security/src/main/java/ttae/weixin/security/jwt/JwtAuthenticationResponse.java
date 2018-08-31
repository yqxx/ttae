package ttae.weixin.security.jwt;

import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable {
	
	private static final long serialVersionUID = 1250166508152483573L;

	private final String token;

	private final long expires_in;

	public JwtAuthenticationResponse(String token, long expires_in) {
		this.token = token;
		this.expires_in = expires_in;
	}

	public String getToken() {
		return this.token;
	}

	public long getExpires_in() {
		return expires_in;
	}

}
