package nekrashevich.artgallery.controller;

import nekrashevich.artgallery.model.Artist;
import nekrashevich.artgallery.service.ArtistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchArtistsController {

    private final ArtistService artistService;

    public SearchArtistsController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/search-artists")
    public String searchArtists(@RequestParam("query") String query, Model model) {
        List<Artist> foundArtists = artistService.searchArtists(query);

        if (foundArtists.isEmpty()) {
            // Если не найдено ни одного художника, вернуть страницу с сообщением об отсутствии результатов
            model.addAttribute("message", "Художники по вашему запросу не найдены.");
            return "search-artists-results";
        } else {
            // Если найдены художники, вернуть страницу с найденными художниками
            model.addAttribute("artists", foundArtists);
            return "search-artists-results";
        }
    }
}
