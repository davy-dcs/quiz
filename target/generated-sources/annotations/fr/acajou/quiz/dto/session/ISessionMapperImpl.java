package fr.acajou.quiz.dto.session;

import fr.acajou.quiz.domain.Quiz;
import fr.acajou.quiz.domain.Session;
import java.util.Date;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-10T10:24:56+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class ISessionMapperImpl implements ISessionMapper {

    @Override
    public SessionResponse sessionToSessionResponse(Session session) {
        if ( session == null ) {
            return null;
        }

        UUID quiz = null;
        Integer timer = null;
        UUID uuid = null;
        Date date = null;

        quiz = sessionQuizUuid( session );
        timer = session.getTimer();
        uuid = session.getUuid();
        date = session.getDate();

        UUID user = null;

        SessionResponse sessionResponse = new SessionResponse( timer, uuid, date, quiz, user );

        return sessionResponse;
    }

    @Override
    public Session sessionRequestToSession(SessionRequest sessionRequest) {
        if ( sessionRequest == null ) {
            return null;
        }

        Session.SessionBuilder session = Session.builder();

        session.quiz( sessionRequestToQuiz( sessionRequest ) );
        session.timer( sessionRequest.timer() );

        return session.build();
    }

    private UUID sessionQuizUuid(Session session) {
        Quiz quiz = session.getQuiz();
        if ( quiz == null ) {
            return null;
        }
        return quiz.getUuid();
    }

    protected Quiz sessionRequestToQuiz(SessionRequest sessionRequest) {
        if ( sessionRequest == null ) {
            return null;
        }

        Quiz.QuizBuilder quiz = Quiz.builder();

        quiz.uuid( sessionRequest.quiz() );

        return quiz.build();
    }
}
