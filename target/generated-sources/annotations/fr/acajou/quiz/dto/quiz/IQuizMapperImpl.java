package fr.acajou.quiz.dto.quiz;

import fr.acajou.quiz.domain.Category;
import fr.acajou.quiz.domain.Difficulty;
import fr.acajou.quiz.domain.Quiz;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-11T11:27:04+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class IQuizMapperImpl implements IQuizMapper {

    @Override
    public QuizResponse quizToQuizResponse(Quiz quiz) {
        if ( quiz == null ) {
            return null;
        }

        UUID uuid = null;
        String title = null;
        String description = null;
        Integer numberOfQuestions = null;
        Category category = null;
        Difficulty difficulty = null;

        uuid = quiz.getUuid();
        title = quiz.getTitle();
        description = quiz.getDescription();
        numberOfQuestions = quiz.getNumberOfQuestions();
        category = quiz.getCategory();
        difficulty = quiz.getDifficulty();

        QuizResponse quizResponse = new QuizResponse( uuid, title, description, numberOfQuestions, category, difficulty );

        return quizResponse;
    }

    @Override
    public Quiz quizRequestToQuiz(QuizRequest quizRequest) {
        if ( quizRequest == null ) {
            return null;
        }

        Quiz.QuizBuilder quiz = Quiz.builder();

        quiz.title( quizRequest.title() );
        quiz.description( quizRequest.description() );
        quiz.category( quizRequest.category() );
        quiz.difficulty( quizRequest.difficulty() );

        return quiz.build();
    }
}
