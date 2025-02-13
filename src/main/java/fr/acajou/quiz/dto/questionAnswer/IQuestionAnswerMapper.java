package fr.acajou.quiz.dto.questionAnswer;

import fr.acajou.quiz.dto.question.IQuestionMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IQuestionAnswerMapper {
    IQuestionMapper INSTANCE = Mappers.getMapper(IQuestionMapper.class);
}
