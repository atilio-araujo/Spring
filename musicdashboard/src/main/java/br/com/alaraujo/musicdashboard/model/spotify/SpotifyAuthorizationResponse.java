package br.com.alaraujo.musicdashboard.model.spotify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyAuthorizationResponse {

	private String accessToken;

	private String tokenType;

	private String expiresIn;

	private SpotifyResponseError error;

	public String getAccess_token() {
		return accessToken;
	}

	public void setAccess_token(String access_token) {
		this.accessToken = access_token;
	}

	public String getToken_type() {
		return tokenType;
	}

	public void setToken_type(String token_type) {
		this.tokenType = token_type;
	}

	public String getExpires_in() {
		return expiresIn;
	}

	public void setExpires_in(String expires_in) {
		this.expiresIn = expires_in;
	}

	public SpotifyResponseError getError() {
		return error;
	}

	public void setError(SpotifyResponseError error) {
		this.error = error;
	}

}
