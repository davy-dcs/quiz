package fr.acajou.quiz.dto.mapper;

import fr.acajou.quiz.domain.Question;
import fr.acajou.quiz.dto.QuestionDTO;
import fr.acajou.quiz.exception.QuestionNotFoundException;
import fr.acajou.quiz.repository.IQuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class QuestionMapper {

    private final IQuestionRepository questionRepository;

    //entité vers dto
    public QuestionDTO entityToDto(Question question){
        return new QuestionDTO(question.getUuid(),
                question.getValue(),
                question.getDifficulty(),
                question.getCategories());
    }

    //dto vers entité
    public Question dtoToEntity(QuestionDTO questionDTO, Long id){
        return new Question(id,
                questionDTO.uuid(),
                questionDTO.value(),
                questionDTO.difficulty(),
                questionDTO.categories(),
                new ArrayList<>());
    }
}
