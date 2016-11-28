package br.com.alaraujo.musicdashboard.model;

import java.util.ArrayList;
import java.util.List;

import br.com.alaraujo.musicdashboard.model.spotify.SpotifyAlbum;
import br.com.alaraujo.musicdashboard.model.spotify.SpotifyArtist;
import br.com.alaraujo.musicdashboard.model.spotify.SpotifyTrack;

public class ArtistProfile {

	private SpotifyArtist artist;
	private List<SpotifyTrack> topTracks = new ArrayList<SpotifyTrack>();
	private List<SpotifyAlbum> albums = new ArrayList<SpotifyAlbum>();
	private List<SpotifyAlbum> singles = new ArrayList<SpotifyAlbum>();
	private List<SpotifyAlbum> appearsOn = new ArrayList<SpotifyAlbum>();
	private List<SpotifyAlbum> compilations = new ArrayList<SpotifyAlbum>();

	public SpotifyArtist getArtist() {
		return artist;
	}

	public void setArtist(SpotifyArtist artist) {
		this.artist = artist;
	}

	public List<SpotifyTrack> getTopTracks() {
		return topTracks;
	}

	public void setTopTracks(List<SpotifyTrack> topTracks) {
		this.topTracks = topTracks;
	}

	public List<SpotifyAlbum> getAlbums() {
		return albums;
	}

	public void setAlbums(List<SpotifyAlbum> albums) {
		this.albums = albums;
	}

	public List<SpotifyAlbum> getSingles() {
		return singles;
	}

	public void setSingles(List<SpotifyAlbum> singles) {
		this.singles = singles;
	}

	public List<SpotifyAlbum> getAppearsOn() {
		return appearsOn;
	}

	public void setAppearsOn(List<SpotifyAlbum> appearsOn) {
		this.appearsOn = appearsOn;
	}

	public List<SpotifyAlbum> getCompilations() {
		return compilations;
	}

	public void setCompilations(List<SpotifyAlbum> compilations) {
		this.compilations = compilations;
	}

}
