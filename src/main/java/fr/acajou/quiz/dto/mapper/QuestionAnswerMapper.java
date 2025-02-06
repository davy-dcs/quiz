package fr.acajou.quiz.dto.mapper;

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
    public QuestionAnswerDTO entityToDto(QuestionAnswer questionAnswer) {
        return new QuestionAnswerDTO(questionAnswer.getUuid(),
                questionAnswer.getQuestion(),
                questionAnswer.getAnswer(),
                questionAnswer.isCorrect()
        );
    }

    //dto vers entité
    public QuestionAnswer dtoToEntity(QuestionAnswerDTO questionAnswerDTO, Long id){
        return new QuestionAnswer(id,
                questionAnswerDTO.uuid(),
                questionAnswerDTO.question(),
                questionAnswerDTO.answer(),
                questionAnswerDTO.correct());
    }
}
