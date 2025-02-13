package fr.acajou.quiz.dto.mapper;

import fr.acajou.quiz.domain.Answer;
import fr.acajou.quiz.dto.AnswerDTO;
import fr.acajou.quiz.repository.IAnswerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnswerMapper {

    private IAnswerRepository answerRepository;

    //entité vers dto
    public AnswerDTO entityToDto(Answer answer){
        return new AnswerDTO(answer.getUuid(), answer.getValue());
    }

    //dto vers entité
    public Answer dtoToEntity(AnswerDTO answerDTO, Long id){
        return new Answer(id,answerDTO.uuid(),answerDTO.value());
    }
}
