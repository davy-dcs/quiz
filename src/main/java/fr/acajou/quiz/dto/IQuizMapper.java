package fr.acajou.quiz.dto;

import fr.acajou.quiz.domain.Quiz;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IQuizMapper {

    IQuizMapper INSTANCE = Mappers.getMapper(IQuizMapper.class);

    QuizResponse quizToQuizResponse(Quiz quiz);
    Quiz quizRequestToQuiz(QuizRequest quizRequest);
}
