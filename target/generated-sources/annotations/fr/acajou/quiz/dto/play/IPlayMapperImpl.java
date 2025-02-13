package fr.acajou.quiz.dto.play;

import fr.acajou.quiz.domain.Play;
import fr.acajou.quiz.domain.Session;
import fr.acajou.quiz.domain.Users;
import fr.acajou.quiz.dto.session.SessionUuid;
import fr.acajou.quiz.dto.user.UserUuid;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-13T16:14:59+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class IPlayMapperImpl implements IPlayMapper {

    @Override
    public Play playRequestToPlay(PlayRequest playRequest) {
        if ( playRequest == null ) {
            return null;
        }

        Play.PlayBuilder play = Play.builder();

        play.users( userUuidToUsers( playRequest.userUuid() ) );
        play.session( sessionUuidToSession( playRequest.sessionUuid() ) );

        return play.build();
    }

    @Override
    public PlayResponse playToPlayResponse(Play play) {
        if ( play == null ) {
            return null;
        }

        PlayResponse playResponse = new PlayResponse();

        return playResponse;
    }

    @Override
    public List<PlayResponse> plaiesToPlaiesResponses(List<Play> plaies) {
        if ( plaies == null ) {
            return null;
        }

        List<PlayResponse> list = new ArrayList<PlayResponse>( plaies.size() );
        for ( Play play : plaies ) {
            list.add( playToPlayResponse( play ) );
        }

        return list;
    }

    protected Users userUuidToUsers(UserUuid userUuid) {
        if ( userUuid == null ) {
            return null;
        }

        Users.UsersBuilder users = Users.builder();

        users.uuid( userUuid.uuid() );

        return users.build();
    }

    protected Session sessionUuidToSession(SessionUuid sessionUuid) {
        if ( sessionUuid == null ) {
            return null;
        }

        Session.SessionBuilder session = Session.builder();

        session.uuid( sessionUuid.uuid() );

        return session.build();
    }
}
