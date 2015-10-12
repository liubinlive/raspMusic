package fr.motaz.rasp.music.ws.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import fr.motaz.rasp.music.model.Music;
import fr.motaz.rasp.music.player.Player;
import fr.motaz.rasp.music.player.Playlist;
import fr.motaz.rasp.music.storage.StorageService;
import fr.motaz.rasp.music.ws.util.MapperUtil;

public class PlaylistResource {
	protected static final Logger logger = LogManager.getLogger(PlaylistResource.class);
	@Autowired
	private Player player;
	@Autowired
	private StorageService storageService;

	@GET
	public Playlist getPlaylist() {
		return player.getPlaylist();
	}

	@GET
	@Path("/current")
	public Music getCurrent() throws Exception {
		logger.trace("getCurrent");
		return player.getPlaylist().getCurrent();
	}

	@PUT
	@Path("/add")
	@Consumes("application/json")
	public Response addMusic(String musicStr) throws Exception {
		logger.trace("addMusic " + musicStr);
		Music music = MapperUtil.readAsObjectOf(Music.class, musicStr);
		if (music.getId() == null) {
			player.getPlaylist().add(music);
		} else {
			storageService.addYoutubeVideo(music);
		}

		return Response.ok().build();
	}

	@PUT
	@Path("/remove")
	@Consumes("application/json")
	public Response removeMusic(String musicStr) throws Exception {
		logger.trace("addMusic " + musicStr);
		Music music = MapperUtil.readAsObjectOf(Music.class, musicStr);
		player.getPlaylist().remove(music);

		return Response.ok().build();
	}
}
