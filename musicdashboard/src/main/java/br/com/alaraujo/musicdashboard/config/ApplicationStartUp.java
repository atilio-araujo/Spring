package br.com.alaraujo.musicdashboard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import br.com.alaraujo.musicdashboard.service.SpotifyService;

@Component
public class ApplicationStartUp implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private SpotifyService spotifyService;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent arg0) {
		this.spotifyService.setAccessToken(this.spotifyService.doClientAuthorizationFlow());
	}

}
