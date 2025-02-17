package fr.acajou.quiz.service;

import fr.acajou.quiz.domain.Category;
import fr.acajou.quiz.domain.Difficulty;
import fr.acajou.quiz.domain.Question;

import java.util.List;

public interface IQuestionService {
    List<Question> find(Category category, Difficulty difficulty);
}
