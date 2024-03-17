package nekrashevich.artgallery.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.v3.oas.annotations.tags.Tag;
import nekrashevich.artgallery.model.Painting;
import nekrashevich.artgallery.service.PaintingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping
@Tag(name = "Home Page", description = "Controller for showing home page with paintings")
public class PaintingController {
    private static final Logger logger = LoggerFactory.getLogger(PaintingController.class);

    private final PaintingService paintingService;

    public PaintingController(PaintingService paintingService) {
        this.paintingService = paintingService;
    }

    @GetMapping("/artgallery-home")
    public String showHomePage(Model model) {
        List<Painting> paintings = paintingService.getAllPaintings();
        logger.info("Retrieved {} paintings", paintings.size());
        model.addAttribute("paintings", paintings);
        return "home-page";
    }

    @GetMapping("/painting-details")
    public String showPaintingDetails(Model model, @RequestParam("id") UUID id) {
        logger.info("Получение информации о картинах с идентификатором {}", id);
        Optional<Painting> painting = paintingService.getPaintingById(id);
        painting.ifPresent(value -> {
            model.addAttribute("painting", value); // Передача объекта картины в шаблон
            // Получение картин данного автора
            List<Painting> artistPaintings = paintingService.getPaintingsByArtistId(value.getArtist().getId());
            model.addAttribute("otherPaintings", artistPaintings); // Добавляем список других работ этого автора в модель
        });
        return "painting-details";
    }


}
