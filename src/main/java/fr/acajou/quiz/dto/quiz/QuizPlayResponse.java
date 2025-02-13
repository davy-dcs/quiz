package fr.acajou.quiz.dto.quiz;

import fr.acajou.quiz.domain.Category;
import fr.acajou.quiz.dto.questionAnswer.QuestionAnswersResponse;

import java.util.List;

public record QuizPlayResponse(
        String title,
        String description,
        Category category,
        List<QuestionAnswersResponse> questions
) {
}
