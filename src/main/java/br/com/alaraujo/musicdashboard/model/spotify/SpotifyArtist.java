package br.com.alaraujo.musicdashboard.model.spotify;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyArtist {

	private String id;

	private SpotifyExternalURLs external_urls;

	private SpotifyFollowers followers;

	private List<String> genres;

	private String href;

	private List<SpotifyImage> images;

	private String name;

	private int popularity;

	private String type;

	private String uri;

	public SpotifyFollowers getFollowers() {
		return followers;
	}

	public void setFollowers(SpotifyFollowers followers) {
		this.followers = followers;
	}

	public SpotifyExternalURLs getExternal_urls() {
		return external_urls;
	}

	public void setExternal_urls(SpotifyExternalURLs external_urls) {
		this.external_urls = external_urls;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
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

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
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

}
