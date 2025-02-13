package fr.acajou.quiz.service.impl;

import fr.acajou.quiz.domain.Category;
import fr.acajou.quiz.domain.Difficulty;
import fr.acajou.quiz.domain.Question;
import fr.acajou.quiz.dto.QuestionDTO;
import fr.acajou.quiz.dto.mapper.QuestionMapper;
import fr.acajou.quiz.exception.QuestionListNotFoundException;
import fr.acajou.quiz.exception.QuestionNotFoundException;
import fr.acajou.quiz.repository.IQuestionRepository;
import fr.acajou.quiz.service.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {

    private final IQuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public QuestionDTO createQuestion(QuestionDTO questionDTO) {
        Question question = new Question(null,null,questionDTO.value(),questionDTO.difficulty(),questionDTO.categories(), new ArrayList<>());
        Question savedQuestion = questionRepository.save(question);
        return questionMapper.entityToDto(savedQuestion);
    }

    public QuestionDTO updateQuestion(QuestionDTO questionDTO) {
        Long id = getId(questionDTO);
        Question updateQuestion = questionMapper.dtoToEntity(questionDTO, id);
        Question savedQuestion = questionRepository.save(updateQuestion);
        return questionMapper.entityToDto(savedQuestion);
    }

    public QuestionDTO getQuestionbyUUID(UUID uuid) {
        Optional<Question> question = questionRepository.findByUuid(uuid);
        return question.map(questionMapper::entityToDto).orElseThrow(() -> new QuestionNotFoundException("Question Not Found : L'uuid question "+uuid+" n'a pas été trouvé"));
    }

    public void deleteQuestionbyUUID(UUID uuid) {
        Optional<Question> question = questionRepository.findByUuid(uuid);
        Question questionSup = question.orElseThrow(() -> new QuestionNotFoundException("Question Not Found : L'uuid question "+uuid+" n'a pas été trouvé"));
        questionRepository.delete(questionSup);
    }

    public Long getId(QuestionDTO questionDTO) {
        UUID uuid = questionDTO.uuid();
        Question questionEntity = questionRepository.findByUuid(uuid)
                .orElseThrow(() -> new QuestionNotFoundException("Question Not Found : L'uuid question " + uuid + " n'a pas été trouvé"));
        return questionEntity.getId();
    }

    public Question getbyUUID(UUID uuid) {
        return questionRepository.findByUuid(uuid)
                .orElseThrow(() -> new QuestionNotFoundException("Question Not Found : L'uuid question " + uuid + " n'a pas été trouvé"));
    }
}
