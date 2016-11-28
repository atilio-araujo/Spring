package br.com.alaraujo.musicdashboard.constant;

public enum SpotifyAlbumType {

	ALBUM("album"),
	SINGLE("single"),
	COMPILATION("compilation"),
	APPEARS_ON("appears_on");

	private String value;

	private SpotifyAlbumType(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
