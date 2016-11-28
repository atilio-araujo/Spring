package br.com.alaraujo.musicdashboard.model.spotify;

import java.util.List;

public class SpotifySimpleAlbum {

	private List<SpotifySimpleArtist> artists;

	private SpotifyExternalURLs externalUrls;

	private String href;

	private String id;

	private List<SpotifyImage> images;

	private String name;

	private String type;

	private String uri;

	private List<String> availableMarkets;

	public SpotifyExternalURLs getExternalUrls() {
		return externalUrls;
	}

	public void setExternalUrls(SpotifyExternalURLs externalUrls) {
		this.externalUrls = externalUrls;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<SpotifyImage> getImages() {
		return images;
	}

	public void setImages(List<SpotifyImage> images) {
		this.images = images;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public List<String> getAvailableMarkets() {
		return availableMarkets;
	}

	public void setAvailableMarkets(List<String> availableMarkets) {
		this.availableMarkets = availableMarkets;
	}

	public List<SpotifySimpleArtist> getArtists() {
		return artists;
	}

	public void setArtists(List<SpotifySimpleArtist> artists) {
		this.artists = artists;
	}

}
