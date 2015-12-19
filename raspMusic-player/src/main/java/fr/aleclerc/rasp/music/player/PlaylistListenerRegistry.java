package fr.aleclerc.rasp.music.player;

public interface PlaylistListenerRegistry {
	public void addPlaylistListener(PlaylistListener listener);
	public void removePlaylistListener(PlaylistListener listener);
	
}