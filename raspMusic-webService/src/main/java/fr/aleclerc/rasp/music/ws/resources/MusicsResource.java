package fr.aleclerc.rasp.music.ws.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import fr.aleclerc.rasp.music.api.pojo.Music;
import fr.aleclerc.rasp.music.storage.music.MusicStorage;
	

@Path("/musics")
public class MusicsResource {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MusicStorage musicStorage;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Music> getMusics() throws Exception{
		LOGGER.debug("Get all musics");
		return  musicStorage.getAll();
	}
	

}
