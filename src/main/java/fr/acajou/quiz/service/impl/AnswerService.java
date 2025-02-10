package fr.acajou.quiz.service.impl;

import fr.acajou.quiz.domain.Answer;
import fr.acajou.quiz.dto.AnswerDTO;
import fr.acajou.quiz.dto.mapper.AnswerMapper;
import fr.acajou.quiz.exception.AnswerNotFoundException;
import fr.acajou.quiz.repository.IAnswerRepository;
import fr.acajou.quiz.service.IAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnswerService implements IAnswerService {

    private final IAnswerRepository answerRepository;
    private final AnswerMapper answerMapper;

    public AnswerDTO createAnswer(AnswerDTO answerDTO) {
        Answer answer = new Answer(null,null,answerDTO.value());
        Answer savedAnswer = answerRepository.save(answer);
        return answerMapper.entityToDto(savedAnswer);
    }

    public AnswerDTO updateAnswer(AnswerDTO answerDTO) {
        Long id = getId(answerDTO);
        Answer updateAnswer = answerMapper.dtoToEntity(answerDTO, id);
        Answer savedAnswer= answerRepository.save(updateAnswer);
        return answerMapper.entityToDto(savedAnswer);
    }

    public AnswerDTO getAnswerbyUUID(UUID uuid) {
        Optional<Answer> answer = answerRepository.findByUuid(uuid);
        return answer.map(answerMapper::entityToDto).orElseThrow(() -> new AnswerNotFoundException("Answer Not Found : L'uuid answer "+uuid+" n'a pas été trouvé"));
    }

    public void deleteAnswerbyUUID(UUID uuid) {
        Optional<Answer> answer = answerRepository.findByUuid(uuid);
        Answer answerSup = answer.orElseThrow(() -> new AnswerNotFoundException("Answer Not Found : L'uuid answer "+uuid+" n'a pas été trouvé"));
        answerRepository.delete(answerSup);
    }
    public Long getId(AnswerDTO answerDTO) {
        UUID uuid = answerDTO.uuid();
        Answer answerEntity = answerRepository.findByUuid(uuid)
                .orElseThrow(() -> new AnswerNotFoundException("Answer Not Found : L'uuid answer " + uuid + " n'a pas été trouvé"));
        return answerEntity.getId();
    }
    public Answer getbyUUID(Answer answer) {
        UUID uuid = answer.getUuid();
        Answer answerEntity = answerRepository.findByUuid(uuid)
                .orElseThrow(() -> new AnswerNotFoundException("Answer Not Found : L'uuid answer " + uuid + " n'a pas été trouvé"));
        return answerEntity;
    }
}
