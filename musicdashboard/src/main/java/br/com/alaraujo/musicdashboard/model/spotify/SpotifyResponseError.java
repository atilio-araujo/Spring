package br.com.alaraujo.musicdashboard.model.spotify;

public class SpotifyResponseError {

	public Integer status;

	public String message;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
