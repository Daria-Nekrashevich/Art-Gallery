package nekrashevich.artgallery.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.v3.oas.annotations.tags.Tag;
import nekrashevich.artgallery.model.Artist;
import nekrashevich.artgallery.model.Painting;
import nekrashevich.artgallery.service.ArtistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping
@Tag(name = "Artists Page", description = "Controller for showing artists page")
public class ArtistController {
    private static final Logger logger = LoggerFactory.getLogger(ArtistController.class);

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/artists")
    public String showArtistsPage(Model model) {
        List<Artist> artists = artistService.getAllArtists();
        logger.info("Retrieved {} artists", artists.size());
        model.addAttribute("artists", artists);
        return "artists";
    }

    @GetMapping("/artist-details")
    public String showArtistDetails(@RequestParam("id") UUID id, Model model) {
        logger.info("Получение информации о художнике с идентификатором {}", id);
        Artist artist = artistService.getArtistById(id);
        List<Painting> paintings = artistService.getPaintingsByArtistId(id);
        model.addAttribute("artist", artist);
        model.addAttribute("paintings", paintings);
        return "artist-details";
    }
}
