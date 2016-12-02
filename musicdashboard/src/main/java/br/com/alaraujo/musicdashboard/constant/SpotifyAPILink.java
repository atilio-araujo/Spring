package br.com.alaraujo.musicdashboard.constant;

public enum SpotifyAPILink {

	MAIN("https://api.spotify.com/v1/"),
	SEARCH("https://api.spotify.com/v1/search?q="),
	ARTISTS("https://api.spotify.com/v1/artists/"),
	ALBUMS("https://api.spotify.com/v1/albums/"),
	AUTHENTICATION("https://accounts.spotify.com/api/");

	private String uri;

	private SpotifyAPILink(String uri){
		this.uri = uri;
	}

	public String getUri() {
		return uri;
	}

}
