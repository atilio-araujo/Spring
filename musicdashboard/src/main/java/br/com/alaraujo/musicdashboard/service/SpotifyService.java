package br.com.alaraujo.musicdashboard.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alaraujo.musicdashboard.constant.Services;
import br.com.alaraujo.musicdashboard.constant.SpotifyAPILink;
import br.com.alaraujo.musicdashboard.constant.SpotifyAlbumType;
import br.com.alaraujo.musicdashboard.constant.SpotifyAuthorizationError;
import br.com.alaraujo.musicdashboard.dao.ServiceAuthorizationKeyDAO;
import br.com.alaraujo.musicdashboard.model.ArtistProfile;
import br.com.alaraujo.musicdashboard.model.ServiceAuthorizationKey;
import br.com.alaraujo.musicdashboard.model.spotify.SpotifyAlbum;
import br.com.alaraujo.musicdashboard.model.spotify.SpotifyArtist;
import br.com.alaraujo.musicdashboard.model.spotify.SpotifyArtistSearchResponse;
import br.com.alaraujo.musicdashboard.model.spotify.SpotifyAuthorizationResponse;
import br.com.alaraujo.musicdashboard.model.spotify.SpotifyPage;
import br.com.alaraujo.musicdashboard.model.spotify.SpotifySimpleArtist;
import br.com.alaraujo.musicdashboard.model.spotify.SpotifyTopTracksResponse;
import br.com.alaraujo.musicdashboard.model.spotify.SpotifyTrack;

@Service
public class SpotifyService {

	private static final String CLIENT_ID = "Client ID";
	private static final String CLIENT_SECRET = "Client Secret";

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ServiceAuthorizationKeyDAO authorizationKeyDAO;

	@Autowired
	private ObjectMapper jsonMapper;

	private String accessToken;

	public List<SpotifyArtist> getArtistByName(String artistName) throws JsonParseException, JsonMappingException, IOException{

		List<SpotifyArtist> artistList = new ArrayList<SpotifyArtist>();

		try{
			ResponseEntity<SpotifyArtistSearchResponse> response = restTemplate.exchange(
			        SpotifyAPILink.SEARCH.getUri() + artistName + "&type=artist",
			        HttpMethod.GET,
			        this.createAuthenticatedHeader(),
			        SpotifyArtistSearchResponse.class);

			if ( response != null && response.getBody() != null 
					&& response.getBody().getArtists() != null && response.getBody().getArtists().getItems() != null){
				artistList  = response.getBody().getArtists().getItems();
			}

		} catch (HttpClientErrorException ex){
			SpotifyAuthorizationResponse error = this.jsonMapper.readValue(ex.getResponseBodyAsByteArray(), SpotifyAuthorizationResponse.class);
			if ( error.getError() != null && error.getError().getStatus() != null ) { 
				if ( error.getError().getStatus().equals(new Integer(SpotifyAuthorizationError.UNAUTHORIZED_STATUS_CODE.getValue())) 
						&& error.getError().getMessage().equals(SpotifyAuthorizationError.UNAUTHORIZED_TOKEN_EXPIRED.getValue())){
					this.accessToken = this.doClientAuthorizationFlow();
					artistList = this.getArtistByName(artistName);
				}
			}
			throw ex;
		}


		return artistList;
	}

	public SpotifyArtist getArtistByID(String artistID) throws JsonParseException, JsonMappingException, IOException{
		SpotifyArtist artist = new SpotifyArtist();

		try{
			ResponseEntity<SpotifyArtist> response = restTemplate.exchange(SpotifyAPILink.ARTISTS.getUri() + artistID,
					HttpMethod.GET,
					this.createAuthenticatedHeader(),
					SpotifyArtist.class);

			if ( response != null && response.getBody() != null ){
				artist = response.getBody();
			}

		} catch (HttpClientErrorException ex) {
			SpotifyAuthorizationResponse error = this.jsonMapper.readValue(ex.getResponseBodyAsByteArray(), SpotifyAuthorizationResponse.class);
			if ( error.getError() != null && error.getError().getStatus() != null ) { 
				if ( error.getError().getStatus().equals(new Integer(SpotifyAuthorizationError.UNAUTHORIZED_STATUS_CODE.getValue())) 
						&& error.getError().getMessage().equals(SpotifyAuthorizationError.UNAUTHORIZED_TOKEN_EXPIRED.getValue())){
					this.accessToken = this.doClientAuthorizationFlow();
					artist = this.getArtistByID(artistID);
				}
			}
		}


		return artist;
	}

	public List<SpotifyTrack> getTopTracksByArtistID(String artistID) throws JsonParseException, JsonMappingException, IOException{
		List<SpotifyTrack> trackList = new ArrayList<SpotifyTrack>();

		try{

			ResponseEntity<SpotifyTopTracksResponse> response = restTemplate.exchange(SpotifyAPILink.ARTISTS.getUri() + artistID + "/top-tracks?country=US",
					HttpMethod.GET,
					this.createAuthenticatedHeader(),
					SpotifyTopTracksResponse.class);
	
			if ( response != null && response.getBody() != null && response.getBody().getTracks() != null ){
				trackList = response.getBody().getTracks();
			}

		} catch ( HttpClientErrorException ex ){
			SpotifyAuthorizationResponse error = this.jsonMapper.readValue(ex.getResponseBodyAsByteArray(), SpotifyAuthorizationResponse.class);
			if ( error.getError() != null && error.getError().getStatus() != null ) { 
				if ( error.getError().getStatus().equals(new Integer(SpotifyAuthorizationError.UNAUTHORIZED_STATUS_CODE.getValue())) 
						&& error.getError().getMessage().equals(SpotifyAuthorizationError.UNAUTHORIZED_TOKEN_EXPIRED.getValue())){
					this.accessToken = this.doClientAuthorizationFlow();
					trackList = this.getTopTracksByArtistID(artistID);
				}
			}
		}

		return trackList;
	}

	public ArtistProfile getArtistProfileByID(String artistID) throws JsonParseException, JsonMappingException, IOException{
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

	public List<SpotifyAlbum> getAlbumListByArtist(String artistID) throws JsonParseException, JsonMappingException, IOException {
		List<SpotifyAlbum> albumList = new ArrayList<SpotifyAlbum>();

		try{
			ResponseEntity<SpotifyPage<SpotifyAlbum>> response = restTemplate.exchange(SpotifyAPILink.ARTISTS.getUri() + artistID + "/albums?market=US",
					HttpMethod.GET,
					this.createAuthenticatedHeader(),
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
		} catch ( HttpClientErrorException ex ){
			SpotifyAuthorizationResponse error = this.jsonMapper.readValue(ex.getResponseBodyAsByteArray(), SpotifyAuthorizationResponse.class);
			if ( error.getError() != null && error.getError().getStatus() != null ) { 
				if ( error.getError().getStatus().equals(new Integer(SpotifyAuthorizationError.UNAUTHORIZED_STATUS_CODE.getValue())) 
						&& error.getError().getMessage().equals(SpotifyAuthorizationError.UNAUTHORIZED_TOKEN_EXPIRED.getValue())){
					this.accessToken = this.doClientAuthorizationFlow();
					albumList = this.getAlbumListByArtist(artistID);
				}
			}
		}

		return albumList;
	}

	public String doClientAuthorizationFlow() {
		String authKey = "";
		ServiceAuthorizationKey clientID = this.authorizationKeyDAO.getValueByKeyAndService(CLIENT_ID, Services.SPOTIFY);
		ServiceAuthorizationKey clientSecret = this.authorizationKeyDAO.getValueByKeyAndService(CLIENT_SECRET, Services.SPOTIFY);

		if ( StringUtils.isNotBlank(clientID.getValue()) && StringUtils.isNotBlank(clientSecret.getValue()) ){			
			String resquestAuthKey = Base64.encodeBase64String((clientID.getValue() + ":" + clientSecret.getValue()).getBytes(Charset.forName("UTF-8")));
			
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.AUTHORIZATION, "Basic " + resquestAuthKey);
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

			HttpEntity<String> entity = new HttpEntity<String>(headers);

			try{
				ResponseEntity<SpotifyAuthorizationResponse> response = restTemplate.exchange(SpotifyAPILink.AUTHENTICATION.getUri() + "token?grant_type=client_credentials", 
						HttpMethod.POST, entity, SpotifyAuthorizationResponse.class);

				if ( response != null && response.getBody() != null ){
					authKey = response.getBody().getAccess_token();
				}

			} catch (HttpClientErrorException ex){
				ex.printStackTrace();
			}

		}

		return authKey;
	}

	private HttpEntity<String> createAuthenticatedHeader(){
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + this.accessToken);

		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return entity;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String acessToken) {
		this.accessToken = acessToken;
	}

}
