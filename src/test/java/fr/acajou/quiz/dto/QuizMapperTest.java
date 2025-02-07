package fr.acajou.quiz.dto;

import fr.acajou.quiz.domain.Quiz;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static fr.acajou.quiz.domain.Category.HISTORY;
import static fr.acajou.quiz.domain.Difficulty.EASY;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class QuizMapperTest {
    @Test
    public void shouldMapQuizToQuizResponse() {
        //given
        Quiz quiz = new Quiz(1L, "titre", "description", 20, HISTORY, EASY, new ArrayList<>());

        //when
        QuizResponse quizResponse = IQuizMapper.INSTANCE.quizToQuizResponse(quiz);

        //then
        assertThat(quizResponse).isNotNull();
        assertThat(quizResponse.title()).isEqualTo( "titre" );
        assertThat(quizResponse.description()).isEqualTo( "description" );
        assertThat(quizResponse.numberOfQuestions()).isEqualTo(20);
        assertThat(quizResponse.category()).isEqualTo(HISTORY);
        assertThat(quizResponse.difficulty()).isEqualTo(EASY);
    }
}
