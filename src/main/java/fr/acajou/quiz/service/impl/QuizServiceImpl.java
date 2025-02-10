package fr.acajou.quiz.service.impl;

import fr.acajou.quiz.dto.quiz.IQuizMapper;
import fr.acajou.quiz.dto.quiz.QuizRequest;
import fr.acajou.quiz.dto.quiz.QuizResponse;
import fr.acajou.quiz.exception.QuizConflictException;
import fr.acajou.quiz.exception.QuizNotFoundException;
import fr.acajou.quiz.repository.IQuizRepository;
import fr.acajou.quiz.service.IQuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements IQuizService {
    private final IQuizRepository quizRepository;

    @Override
    public QuizResponse post(QuizRequest quizRequest) {
        boolean quizExists = quizRepository.existsByNumberOfQuestionsAndCategoryAndDifficulty(quizRequest.numberOfQuestion(), quizRequest.category(), quizRequest.difficulty());
        if (quizExists) {
            throw new QuizConflictException("Un quiz similaire existe déjà.");
        }

        //TODO Programme ajoutant les questions

        return IQuizMapper.INSTANCE.quizToQuizResponse(quizRepository.save(IQuizMapper.INSTANCE.quizRequestToQuiz(quizRequest)));
    }

    @Override
    public String delete(UUID uuid) {
        if (quizRepository.deleteByUuid(uuid)) {
            return "Quiz supprimé";
        } else {
            throw new QuizNotFoundException("Quiz non trouvé");
        }
    }
}
