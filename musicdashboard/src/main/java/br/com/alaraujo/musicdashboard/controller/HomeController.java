package br.com.alaraujo.musicdashboard.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
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
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class HomeController {

	@Autowired
	private SpotifyService artistService;

	@Autowired
	private List<SpotifyArtist> artistSearchResult;

	@RequestMapping(value = "/", method=RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView view = new ModelAndView("artistSearchResult");
		view.addObject("artistList", this.artistSearchResult);
		return view;
	}

	@RequestMapping(value = "/findArtist", method = RequestMethod.GET)
	public ModelAndView findArtist(@RequestParam(value = "artistName", required = false) String artistName) throws Exception{	
		ModelAndView modelAndView = new ModelAndView("artistSearchResult"); 
		this.artistSearchResult.clear();

		if ( StringUtils.isNotBlank(artistName) ){
			this.artistSearchResult.addAll(artistService.getArtistByName(artistName));
		}

		modelAndView.addObject("artistList", this.artistSearchResult);

		return modelAndView;
	}

}
