package fr.acajou.quiz.controller;

import fr.acajou.quiz.domain.*;
import fr.acajou.quiz.service.IPDFService;
import fr.acajou.quiz.service.impl.PDFService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class PDFController {

    public final PDFService pdfService;

    @GetMapping("/pdf")
    public void pdf(HttpServletResponse response) throws IOException {
        //Call current PLAY
        Map<String, Object> dataPlay = createTestData();
        ByteArrayInputStream exportedData = pdfService.generatePdf("generate_pdf", dataPlay);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=score.pdf");
        IOUtils.copy(exportedData, response.getOutputStream());
    }

    private Map<String, Object> createTestData() {

        Long id = new Random().nextLong(100000);
        Long user_id = new Random().nextLong(100000);
        Long session_id = new Random().nextLong(100000);

        UUID uuid_user = UUID.randomUUID();
        UUID uuid_session = UUID.randomUUID();

        Map<String, Object> data = new HashMap<>();
        Play play = new Play();
        play.setId(id);
        play.setScore(90);

        Users user = new Users();
        user.setId(user_id);
        user.setUuid(uuid_user);
        user.setUsername("Test UserName");
        user.setPassword("password");
        user.setRole(new Role("USER"));

        play.setUsers(user);

        Session session = new Session();
        session.setId(session_id);
        session.setUuid(uuid_session);
        session.setDate(new Date());
        session.setTimer(60);
        session.setUser(user);

        Quiz quiz = new Quiz();

        session.setQuiz(quiz);

        play.setSession(session);

        data.put("play", play);
        return data;
    }
}
