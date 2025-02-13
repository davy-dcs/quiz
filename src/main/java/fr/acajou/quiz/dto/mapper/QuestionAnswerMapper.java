package fr.acajou.quiz.dto.mapper;

import fr.acajou.quiz.domain.Answer;
import fr.acajou.quiz.domain.Question;
import fr.acajou.quiz.domain.QuestionAnswer;
import fr.acajou.quiz.dto.QuestionAnswerDTO;
import fr.acajou.quiz.dto.QuestionDTO;
import fr.acajou.quiz.repository.IQuestionAnswerRepository;
import fr.acajou.quiz.repository.IQuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuestionAnswerMapper {

    private final IQuestionAnswerRepository questionAnswerRepository;

    //entité vers dto
    public QuestionAnswerDTO entityToDto(QuestionAnswer questionAnswer, Question question, Answer answer) {
        return new QuestionAnswerDTO(questionAnswer.getUuid(),
                question.getUuid(),
                answer.getUuid(),
                questionAnswer.isCorrect()
        );
    }

    //dto vers entité
    public QuestionAnswer dtoToEntity(QuestionAnswerDTO questionAnswerDTO, Long id, Question question, Answer answer){
        return new QuestionAnswer(id,
                questionAnswerDTO.uuid(),
                question,
                answer,
                questionAnswerDTO.correct());
    }
}
