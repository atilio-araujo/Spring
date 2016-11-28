package br.com.alaraujo.musicdashboard.model.spotify;

import java.util.List;

public class SpotifyAlbum {

	private List<SpotifySimpleArtist> artists;
	
	private String albumType;
	
	private List<String> availableMarkets;
	
	private SpotifyExternalURLs externalUrls;
	
	private List<String> genres;
	
	private String href;
	
	private String id;
	
	private List<SpotifyImage> images;
	
	private String name;
	
	private int popularity;
	
	private String releaseDate;
	
	private String releaseDatePrecision;
	
	private SpotifyPage<SpotifySimpleTrack> tracks;
	
	private String type;
	
	private String uri;

	public List<SpotifySimpleArtist> getArtists() {
		return artists;
	}

	public void setArtists(List<SpotifySimpleArtist> artists) {
		this.artists = artists;
	}

	public String getAlbumType() {
		return albumType;
	}

	public void setAlbumType(String albumType) {
		this.albumType = albumType;
	}

	public List<String> getAvailableMarkets() {
		return availableMarkets;
	}

	public void setAvailableMarkets(List<String> availableMarkets) {
		this.availableMarkets = availableMarkets;
	}

	public SpotifyExternalURLs getExternalUrls() {
		return externalUrls;
	}

	public void setExternalUrls(SpotifyExternalURLs externalUrls) {
		this.externalUrls = externalUrls;
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

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getReleaseDatePrecision() {
		return releaseDatePrecision;
	}

	public void setReleaseDatePrecision(String releaseDatePrecision) {
		this.releaseDatePrecision = releaseDatePrecision;
	}

	public SpotifyPage<SpotifySimpleTrack> getTracks() {
		return tracks;
	}

	public void setTracks(SpotifyPage<SpotifySimpleTrack> tracks) {
		this.tracks = tracks;
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

	public String getAlbum_type() {
		return albumType;
	}

	public void setAlbum_type(String album_type) {
		this.albumType = album_type;
	}

}
