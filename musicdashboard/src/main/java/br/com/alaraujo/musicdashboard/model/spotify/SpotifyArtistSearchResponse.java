package br.com.alaraujo.musicdashboard.model.spotify;

public class SpotifyArtistSearchResponse {

	public String href;

	public SpotifyPage<SpotifyArtist> artists;

	public SpotifyPage<SpotifyArtist> getArtists() {
		return artists;
	}

	public void setArtists(SpotifyPage<SpotifyArtist> artists) {
		this.artists = artists;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

}
