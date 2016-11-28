package br.com.alaraujo.musicdashboard.model.spotify;

import java.util.List;

public class SpotifyTopTracksResponse {

	private List<SpotifyTrack> tracks;

	public List<SpotifyTrack> getTracks() {
		return tracks;
	}

	public void setTracks(List<SpotifyTrack> tracks) {
		this.tracks = tracks;
	}

}
