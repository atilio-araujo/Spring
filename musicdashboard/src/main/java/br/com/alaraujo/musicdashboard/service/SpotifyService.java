package br.com.alaraujo.musicdashboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.alaraujo.musicdashboard.constant.SpotifyAPILink;
import br.com.alaraujo.musicdashboard.model.spotify.SpotifyArtist;
import br.com.alaraujo.musicdashboard.model.spotify.SpotifyArtistSearchResponse;

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
			artistList = response.getBody().getArtists().getItems();
		}

		return artistList;
	}

//	public List<SimpleAlbum> getAlbumListByArtist(String artistID) throws IOException, WebApiException {
//
//		AlbumsForArtistRequest request = this.getSpotifyAPI().getAlbumsForArtist(artistID).build();
//		Page<SimpleAlbum> searchResult = request.get();
//		
//		return searchResult.getItems();
//	}
}
