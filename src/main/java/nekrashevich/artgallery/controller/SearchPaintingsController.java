package nekrashevich.artgallery.controller;

import nekrashevich.artgallery.model.Painting;
import nekrashevich.artgallery.service.PaintingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchPaintingsController {

    private final PaintingService paintingService;

    public SearchPaintingsController(PaintingService paintingService) {
        this.paintingService = paintingService;
    }

    @GetMapping("/search-paintings")
    public String searchPaintings(@RequestParam("query") String query, Model model) {
        List<Painting> foundPaintings = paintingService.searchPaintings(query);

        if (foundPaintings.isEmpty()) {
            // Если не найдено ни одной работы, вернуть страницу с сообщением об отсутствии результатов
            model.addAttribute("message", "Работы по вашему запросу не найдены.");
            return "search-paintings-results";
        } else {
            // Если найдены работы, вернуть страницу с найденными работами
            model.addAttribute("paintings", foundPaintings);
            return "search-paintings-results";
        }
    }
}
