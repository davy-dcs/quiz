package fr.acajou.quiz.dto.play;

import fr.acajou.quiz.domain.Play;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IPlayMapper {
    IPlayMapper INSTANCE = Mappers.getMapper(IPlayMapper.class);

    @Mapping(source = "userUuid.uuid", target = "users.uuid")
    @Mapping(source = "sessionUuid.uuid", target = "session.uuid")
    Play playRequestToPlay(PlayRequest playRequest);
    PlayResponse playToPlayResponse(Play play);
    List<PlayResponse> plaiesToPlaiesResponses(List<Play> plaies);
}
