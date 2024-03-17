package nekrashevich.artgallery.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@Tag(name = "hello", description = "Test description")
public class testController {
    @GetMapping
    public String hello() {
        System.out.println("Hello test");
        return "Hello";
    }
}
