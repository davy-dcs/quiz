package fr.acajou.quiz.dto.questionAnswer;

import fr.acajou.quiz.dto.answer.AnswerResponse;
import fr.acajou.quiz.dto.question.QuestionResponse;

import java.util.List;
import java.util.UUID;

public record QuestionAnswersResponse(
        UUID uuid,
        QuestionResponse question,
        List<AnswerResponse> answers
) {
}
