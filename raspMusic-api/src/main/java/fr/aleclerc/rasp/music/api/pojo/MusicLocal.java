package fr.aleclerc.rasp.music.api.pojo;

public class MusicLocal extends Music {
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "MusicLocal [path=" + path + ", toString()=" + super.toString() + "]";
	}

	
	
}
