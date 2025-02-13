package fr.acajou.quiz.dto.question;

import fr.acajou.quiz.domain.Question;
import fr.acajou.quiz.dto.questionAnswer.QuestionAnswersResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IQuestionMapper {
    IQuestionMapper INSTANCE = Mappers.getMapper(IQuestionMapper.class);

    List<QuestionAnswersResponse> questionsToQuestionsAnswersResponse(List<Question> questions);
}
