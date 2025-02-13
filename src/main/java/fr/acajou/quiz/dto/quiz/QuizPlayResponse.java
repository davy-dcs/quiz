package fr.acajou.quiz.dto.quiz;

import fr.acajou.quiz.domain.Category;
import fr.acajou.quiz.domain.Question;

import java.util.List;

public record QuizPlayResponse(
        //List des questions (dif)
        String title,
        String description,
        Category category,
        //TODO demander a steve pour QuestionAnswer dto
        List<QuestionDtoAVoir> questions

) {
}
