package br.com.alaraujo.musicdashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.alaraujo.musicdashboard.model.spotify.SpotifyArtist;
import br.com.alaraujo.musicdashboard.service.SpotifyService;

@RestController
@RequestMapping("/musicdashboard")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class HomeController {

	@Autowired
	private SpotifyService artistService;

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView home(){
		return new ModelAndView("home");
	}

	@RequestMapping(value = "/findArtist/{artistName}", method = RequestMethod.GET)
	public ModelAndView findArtist(@RequestParam("artistName") String artistName) throws Exception{	
		ModelAndView modelAndView = new ModelAndView("home");
		List<SpotifyArtist> artistList = artistService.getArtistByName(artistName);

		modelAndView.addObject("artistList", artistList);
		return modelAndView;
	}
}
