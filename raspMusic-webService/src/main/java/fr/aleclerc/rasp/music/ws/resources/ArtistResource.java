package fr.aleclerc.rasp.music.ws.resources;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import fr.aleclerc.rasp.music.api.pojo.Artist;
import fr.aleclerc.rasp.music.api.pojo.Music;
import fr.aleclerc.rasp.music.storage.artist.ArtistStorage;
import fr.aleclerc.rasp.music.storage.music.MusicStorage;

@Path("/artist")
public class ArtistResource {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ArtistStorage artistStorage;

	@Autowired
	private MusicStorage musicStorage;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Artist getArtist(@PathParam("artist") String artistName) throws Exception {
		LOGGER.debug("Get artiste : {} ",artistName);
		Optional<Artist> artist = artistStorage.getArtist(artistName);
		if (artist.isPresent()) {
			return artist.get();
		}
		LOGGER.error("Pas d'artiste");
		throw new Exception("pas d artiste");
	}

	@GET
	@Path("{artist}/musics/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Music> getMusics(@PathParam("artist") String artistName) throws Exception {
		LOGGER.debug("Get music form artiste : {} ",artistName);
		Optional<Artist> artist = artistStorage.getArtist(artistName);
		if (artist.isPresent())
		{
			return musicStorage.getMusicsFromArtist(artist.get());
		}
		LOGGER.error("Pas d'artiste");
		throw new Exception("pas d artiste");

		
		
	}

}
