package br.com.alaraujo.musicdashboard.constant;

public enum Services {

	SPOTIFY(1, "Spotify");

	private Integer id;
	private String name;

	private Services(Integer id, String name){
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
