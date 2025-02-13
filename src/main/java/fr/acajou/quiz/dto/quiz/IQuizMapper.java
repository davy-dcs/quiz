package fr.acajou.quiz.dto.quiz;

import fr.acajou.quiz.domain.Quiz;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IQuizMapper {

    IQuizMapper INSTANCE = Mappers.getMapper(IQuizMapper.class);

    QuizResponse quizToQuizResponse(Quiz quiz);
    List<QuizResponse> quizzesToQuizzesResponses(List<Quiz> quizzes);
    Quiz quizRequestToQuiz(QuizRequest quizRequest);
}
