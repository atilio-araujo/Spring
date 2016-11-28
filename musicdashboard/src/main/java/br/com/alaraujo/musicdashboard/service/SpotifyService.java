package br.com.alaraujo.musicdashboard.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.alaraujo.musicdashboard.constant.SpotifyAPILink;
import br.com.alaraujo.musicdashboard.constant.SpotifyAlbumType;
import br.com.alaraujo.musicdashboard.model.ArtistProfile;
import br.com.alaraujo.musicdashboard.model.spotify.SpotifyAlbum;
import br.com.alaraujo.musicdashboard.model.spotify.SpotifyArtist;
import br.com.alaraujo.musicdashboard.model.spotify.SpotifyArtistSearchResponse;
import br.com.alaraujo.musicdashboard.model.spotify.SpotifyPage;
import br.com.alaraujo.musicdashboard.model.spotify.SpotifySimpleArtist;
import br.com.alaraujo.musicdashboard.model.spotify.SpotifyTopTracksResponse;
import br.com.alaraujo.musicdashboard.model.spotify.SpotifyTrack;

@Service
public class SpotifyService {

	@Autowired
	private RestTemplate restTemplate;

	public List<SpotifyArtist> getArtistByName(String artistName) throws Exception{

		List<SpotifyArtist> artistList = new ArrayList<SpotifyArtist>();
		ResponseEntity<SpotifyArtistSearchResponse> response = restTemplate.getForEntity(
		        SpotifyAPILink.SEARCH.getUri() + artistName + "&type=artist",
		        SpotifyArtistSearchResponse.class);

		if ( response != null && response.getBody() != null 
				&& response.getBody().getArtists() != null && response.getBody().getArtists().getItems() != null){
			artistList  = response.getBody().getArtists().getItems();
		}

		return artistList;
	}

	public SpotifyArtist getArtistByID(String artistID){
		SpotifyArtist artist = new SpotifyArtist();

		ResponseEntity<SpotifyArtist> response = restTemplate.getForEntity(SpotifyAPILink.ARTISTS.getUri() + artistID, SpotifyArtist.class);

		if ( response != null && response.getBody() != null ){
			artist = response.getBody();
		}

		return artist;
	}

	public List<SpotifyTrack> getTopTracksByArtistID(String artistID){
		List<SpotifyTrack> trackList = new ArrayList<SpotifyTrack>();

		ResponseEntity<SpotifyTopTracksResponse> response = restTemplate.getForEntity(SpotifyAPILink.ARTISTS.getUri() + artistID + "/top-tracks?country=US", SpotifyTopTracksResponse.class);

		if ( response != null && response.getBody() != null && response.getBody().getTracks() != null ){
			trackList = response.getBody().getTracks();
		}

		return trackList;
	}

	public ArtistProfile getArtistProfileByID(String artistID){
		ArtistProfile artistProfile = new ArtistProfile();

		SpotifyArtist artist = this.getArtistByID(artistID);
		List<SpotifyTrack> topTracks = this.getTopTracksByArtistID(artistID);
		List<SpotifyAlbum> albums = this.getAlbumListByArtist(artistID);

		for( SpotifyAlbum album : albums ){
			if ( album.getAlbumType().equals(SpotifyAlbumType.ALBUM.getValue()) ){
				for ( SpotifySimpleArtist simpleArtist : album.getArtists() ){
					if ( simpleArtist.getId().equals(artistID) ){
						artistProfile.getAlbums().add(album);
						break;
					}
				}
			} else if( album.getAlbumType().equals(SpotifyAlbumType.SINGLE.getValue()) ){
				for ( SpotifySimpleArtist simpleArtist : album.getArtists() ){
					if ( simpleArtist.getId().equals(artistID) ){						
						artistProfile.getSingles().add(album);
						break;
					}
				}
			} else if( album.getAlbumType().equals(SpotifyAlbumType.APPEARS_ON.getValue()) ){
				for ( SpotifySimpleArtist simpleArtist : album.getArtists() ){
					if ( simpleArtist.getId().equals(artistID) ){						
						artistProfile.getAppearsOn().add(album);
						break;
					}
				}
			} else if( album.getAlbumType().equals(SpotifyAlbumType.COMPILATION.getValue()) ){
				for ( SpotifySimpleArtist simpleArtist : album.getArtists() ){
					if ( simpleArtist.getId().equals(artistID) ){						
						artistProfile.getCompilations().add(album);
						break;
					}
				}
			}
		}

		for ( SpotifyTrack track : topTracks ){
			for ( SpotifySimpleArtist simpleArtist : track.getAlbum().getArtists() ){
				if ( simpleArtist.getId().equals(artistID) ){
					artistProfile.getTopTracks().add(track);
					break;
				}
			}
		}

		artistProfile.setArtist(artist);

		return artistProfile;
	}

	public List<SpotifyAlbum> getAlbumListByArtist(String artistID) {
		List<SpotifyAlbum> albumList = new ArrayList<SpotifyAlbum>();

		ResponseEntity<SpotifyPage<SpotifyAlbum>> response = restTemplate.exchange(SpotifyAPILink.ARTISTS.getUri() + artistID + "/albums?market=US",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<SpotifyPage<SpotifyAlbum>>() {});

		if ( response != null && response.getBody() != null && response.getBody().getItems() != null ){
			albumList = response.getBody().getItems();

			String nextPage = response.getBody().getNext();
			while ( StringUtils.isNotBlank(nextPage) ){
				response = restTemplate.exchange(nextPage,
						HttpMethod.GET,
						null,
						new ParameterizedTypeReference<SpotifyPage<SpotifyAlbum>>() {});

				if ( response != null && response.getBody() != null && response.getBody().getItems() != null ){
					albumList.addAll(response.getBody().getItems());

					nextPage = response.getBody().getNext();
				}
			}
		}

		return albumList;
	}

}
