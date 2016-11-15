package br.com.alaraujo.musicdashboard.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.alaraujo.musicdashboard.model.spotify.SpotifyArtist;
import br.com.alaraujo.musicdashboard.service.SpotifyService;

@Controller
@RequestMapping("/musicdashboard")
public class HomeController {

	@Autowired
	private SpotifyService artistService;

	@RequestMapping(method=RequestMethod.GET)
	public String home(){
		return "home";
	}

	@RequestMapping(value = "/findArtist/{artistName}", method = RequestMethod.GET)
	public ModelAndView findArtist(@RequestParam("artistName") String artistName) throws Exception{
		ModelAndView modelAndView = new ModelAndView("home");
		List<SpotifyArtist> artistList = new ArrayList<SpotifyArtist>();
		artistList = artistService.getArtistByName(artistName);
		modelAndView.addObject("artistList", artistList);
		return modelAndView;
	}
}
