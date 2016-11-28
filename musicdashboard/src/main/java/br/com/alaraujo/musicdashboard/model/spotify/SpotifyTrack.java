package br.com.alaraujo.musicdashboard.model.spotify;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyTrack {

	private SpotifySimpleAlbum album;

	private List<SpotifySimpleArtist> artists;

	private List<String> availableMarkets;

	private int discNumber;

	private int duration_ms;

	private boolean explicit;

	private SpotifyExternalURLs externalUrls;

	private String href;

	private String id;

	private String name;

	private int popularity;

	private String previewUrl;

	private int trackNumber;

	private String type;

	private String uri;

	public String getFormattedTrackLength(){
		String formattedTrackLength = "";

		if ( this.duration_ms > 0 ) {
			formattedTrackLength = String.format("%02d:%02d", 
									    TimeUnit.MILLISECONDS.toMinutes(this.duration_ms),
									    TimeUnit.MILLISECONDS.toSeconds(this.duration_ms) - 
									    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(this.duration_ms))
									);
		}

		return formattedTrackLength;
	}
	public List<String> getAvailableMarkets() {
		return availableMarkets;
	}

	public void setAvailableMarkets(List<String> availableMarkets) {
		this.availableMarkets = availableMarkets;
	}

	public int getDiscNumber() {
		return discNumber;
	}

	public void setDiscNumber(int discNumber) {
		this.discNumber = discNumber;
	}

	public int getDuration_ms() {
		return duration_ms;
	}

	public void setDuration_ms(int duration_ms) {
		this.duration_ms = duration_ms;
	}

	public boolean isExplicit() {
		return explicit;
	}

	public void setExplicit(boolean explicit) {
		this.explicit = explicit;
	}

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

	public String getPreviewUrl() {
		return previewUrl;
	}

	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}

	public int getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(int trackNumber) {
		this.trackNumber = trackNumber;
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

	public SpotifySimpleAlbum getAlbum() {
		return album;
	}

	public void setAlbum(SpotifySimpleAlbum album) {
		this.album = album;
	}

	public List<SpotifySimpleArtist> getArtists() {
		return artists;
	}

	public void setArtists(List<SpotifySimpleArtist> artists) {
		this.artists = artists;
	}

}
