package nekrashevich.artgallery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user-profile")
    public String showUserProfile() {
        return "user-profile";
    }
}
