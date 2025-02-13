package fr.acajou.quiz.dto.question;

import fr.acajou.quiz.domain.Question;
import fr.acajou.quiz.domain.QuestionAnswer;
import fr.acajou.quiz.dto.answer.AnswerResponse;
import fr.acajou.quiz.dto.questionAnswer.QuestionAnswersResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-13T16:14:59+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class IQuestionMapperImpl implements IQuestionMapper {

    @Override
    public List<QuestionAnswersResponse> questionsToQuestionsAnswersResponse(List<Question> questions) {
        if ( questions == null ) {
            return null;
        }

        List<QuestionAnswersResponse> list = new ArrayList<QuestionAnswersResponse>( questions.size() );
        for ( Question question : questions ) {
            list.add( questionToQuestionAnswersResponse( question ) );
        }

        return list;
    }

    protected AnswerResponse questionAnswerToAnswerResponse(QuestionAnswer questionAnswer) {
        if ( questionAnswer == null ) {
            return null;
        }

        UUID uuid = null;
        Boolean correct = null;

        uuid = questionAnswer.getUuid();
        correct = questionAnswer.isCorrect();

        String value = null;

        AnswerResponse answerResponse = new AnswerResponse( uuid, value, correct );

        return answerResponse;
    }

    protected List<AnswerResponse> questionAnswerListToAnswerResponseList(List<QuestionAnswer> list) {
        if ( list == null ) {
            return null;
        }

        List<AnswerResponse> list1 = new ArrayList<AnswerResponse>( list.size() );
        for ( QuestionAnswer questionAnswer : list ) {
            list1.add( questionAnswerToAnswerResponse( questionAnswer ) );
        }

        return list1;
    }

    protected QuestionAnswersResponse questionToQuestionAnswersResponse(Question question) {
        if ( question == null ) {
            return null;
        }

        List<AnswerResponse> answers = null;

        answers = questionAnswerListToAnswerResponseList( question.getAnswers() );

        QuestionResponse question1 = null;

        QuestionAnswersResponse questionAnswersResponse = new QuestionAnswersResponse( question1, answers );

        return questionAnswersResponse;
    }
}
