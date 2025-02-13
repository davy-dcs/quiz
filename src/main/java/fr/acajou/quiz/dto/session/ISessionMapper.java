package fr.acajou.quiz.dto.session;

import fr.acajou.quiz.domain.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ISessionMapper {

    ISessionMapper INSTANCE = Mappers.getMapper(ISessionMapper.class);

    @Mapping(source = "quiz.uuid", target = "quiz")
    @Mapping(source = "user.uuid", target = "user")
    SessionResponse sessionToSessionResponse(Session session);
    @Mapping(source = "quiz", target = "quiz.uuid")
    @Mapping(source = "user", target = "user.uuid")
    Session sessionRequestToSession(SessionRequest sessionRequest);

    List<SessionResponse> sessionsToSessionsResponses(List<Session> sessions);
}
