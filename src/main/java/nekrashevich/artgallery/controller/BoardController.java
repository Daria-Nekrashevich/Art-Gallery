package nekrashevich.artgallery.controller;

import nekrashevich.artgallery.model.Board;
import nekrashevich.artgallery.model.Painting;
import nekrashevich.artgallery.model.PaintingOnBoard;
import nekrashevich.artgallery.model.User;
import nekrashevich.artgallery.repository.BoardRepository;
import nekrashevich.artgallery.repository.PaintingOnBoardRepository;
import nekrashevich.artgallery.repository.PaintingRepository;
import nekrashevich.artgallery.repository.UserRepository;
import nekrashevich.artgallery.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
    @Autowired
    private final BoardRepository boardRepository;
    private final PaintingOnBoardRepository paintingOnBoardRepository;
    private final UserRepository userRepository;
    private final PaintingRepository paintingRepository;

    @Autowired
    private UserService userService;

    public BoardController(BoardRepository boardRepository, PaintingOnBoardRepository paintingOnBoardRepository, UserRepository userRepository, PaintingRepository paintingRepository) {
        this.boardRepository = boardRepository;
        this.paintingOnBoardRepository = paintingOnBoardRepository;
        this.userRepository = userRepository;
        this.paintingRepository = paintingRepository;
    }

    @GetMapping("/my-boards")
    public String showMyBoards(Model model) {
        List<Board> boards = boardRepository.findAll();
        Map<UUID, List<Painting>> paintingsMap = new HashMap<>();
        for (Board board : boards) {
            List<Painting> paintings = new ArrayList<>();
            List<PaintingOnBoard> paintingsOnBoard = paintingOnBoardRepository.findByBoardId(board.getId());
            for (PaintingOnBoard paintingOnBoard : paintingsOnBoard) {
                Optional<Painting> optionalPainting = paintingRepository.findById(paintingOnBoard.getPainting().getId());
                optionalPainting.ifPresent(paintings::add);
            }
            paintingsMap.put(board.getId(), paintings);

            // Добавляем вывод данных о картине в консоль
            System.out.println("Для доски с id " + board.getId() + " найдены следующие картины:");
            paintings.forEach(painting -> System.out.println(painting.getName() + " - " + painting.getDescription()));
        }
        model.addAttribute("paintingsMap", paintingsMap);
        model.addAttribute("boards", boards);
        return "my-boards";
    }

    @PostMapping("/create-board")
    public String createBoard(@RequestParam("boardName") String boardName,
                              @RequestParam("boardDescription") String boardDescription) {

        User user = userRepository.findById(UUID.fromString("00000000-0000-0000-0000-000000000000")).orElse(null);
        if (user == null) {

            return "redirect:/error";
        }

        Board board = new Board();
        board.setName(boardName);
        board.setDescription(boardDescription);
        board.setUser(user);

        boardRepository.save(board);

        logger.info("Доска успешно добавлена: " + boardName);

        return "redirect:/my-boards";
    }

    @PostMapping("/remove-painting")
    public String removePainting(@RequestParam("boardId") UUID boardId,
                                 @RequestParam("paintingId") UUID paintingId) {
        paintingOnBoardRepository.deleteByBoardIdAndPaintingId(boardId, paintingId);
        return "redirect:/my-boards";
    }
}
