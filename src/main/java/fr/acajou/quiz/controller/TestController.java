package fr.acajou.quiz.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestController {

    @GetMapping("/admin")
    @Secured("ROLE_ADMIN")
    public String admin() {
        return "Admin page";
    }

    @GetMapping("/user")
    @Secured("ROLE_USER")
    public String user() {
        return "User page";
    }
}
