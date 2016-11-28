package br.com.alaraujo.musicdashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.alaraujo.musicdashboard.model.ArtistProfile;
import br.com.alaraujo.musicdashboard.service.SpotifyService;

@RestController
@RequestMapping("/musicdashboard")
public class ArtistController {

	@Autowired
	private SpotifyService spotifyService;

	@RequestMapping(value = "/artist/{artistID}", method = RequestMethod.GET)
	public ModelAndView artistProfile(@PathVariable("artistID") String artistID){
		ModelAndView view = new ModelAndView("artistProfile");

		ArtistProfile artistProfile = spotifyService.getArtistProfileByID(artistID);
		view.addObject("artistProfile", artistProfile);

		return view;
	}

}
