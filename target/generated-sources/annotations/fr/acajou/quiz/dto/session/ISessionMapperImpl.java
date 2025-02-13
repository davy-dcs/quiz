package fr.acajou.quiz.dto.session;

import fr.acajou.quiz.domain.Quiz;
import fr.acajou.quiz.domain.Session;
import fr.acajou.quiz.domain.Users;
import java.util.Date;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-11T11:27:05+0100",
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
        UUID user = null;
        Integer timer = null;
        UUID uuid = null;
        Date date = null;

        quiz = sessionQuizUuid( session );
        user = sessionUserUuid( session );
        timer = session.getTimer();
        uuid = session.getUuid();
        date = session.getDate();

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
        session.user( sessionRequestToUsers( sessionRequest ) );
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

    private UUID sessionUserUuid(Session session) {
        Users user = session.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getUuid();
    }

    protected Quiz sessionRequestToQuiz(SessionRequest sessionRequest) {
        if ( sessionRequest == null ) {
            return null;
        }

        Quiz.QuizBuilder quiz = Quiz.builder();

        quiz.uuid( sessionRequest.quiz() );

        return quiz.build();
    }

    protected Users sessionRequestToUsers(SessionRequest sessionRequest) {
        if ( sessionRequest == null ) {
            return null;
        }

        Users.UsersBuilder users = Users.builder();

        users.uuid( sessionRequest.user() );

        return users.build();
    }
}
