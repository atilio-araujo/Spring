package br.com.alaraujo.musicdashboard.constant;

public enum SpotifyAuthorizationError {

	UNAUTHORIZED_STATUS_CODE("401"),
	UNAUTHORIZED_TOKEN_EXPIRED("The access token expired");

	private String value;

	private SpotifyAuthorizationError(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
