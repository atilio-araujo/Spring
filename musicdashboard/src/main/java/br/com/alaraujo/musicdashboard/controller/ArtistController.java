package br.com.alaraujo.musicdashboard.controller;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.alaraujo.musicdashboard.model.ArtistProfile;
import br.com.alaraujo.musicdashboard.service.SpotifyService;

@RestController
@RequestMapping("/musicdashboard")
public class ArtistController {

	@Autowired
	private SpotifyService spotifyService;

	@RequestMapping(value = {"/artist/{artistID}", "/artist"}, method = RequestMethod.GET)
	public ModelAndView artistProfile(@PathVariable("artistID") String artistID) throws JsonParseException, JsonMappingException, IOException{
		ModelAndView view = new ModelAndView("artistProfile");

		if ( StringUtils.isNotBlank(artistID) ){			
			ArtistProfile artistProfile = spotifyService.getArtistProfileByID(artistID);
			view.addObject("artistProfile", artistProfile);
		}

		return view;
	}

}
