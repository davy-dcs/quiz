package fr.acajou.quiz.service.impl;

import fr.acajou.quiz.domain.Answer;
import fr.acajou.quiz.domain.Question;
import fr.acajou.quiz.domain.QuestionAnswer;
import fr.acajou.quiz.dto.QuestionAnswerDTO;
import fr.acajou.quiz.dto.QuestionDTO;
import fr.acajou.quiz.dto.mapper.QuestionAnswerMapper;
import fr.acajou.quiz.exception.QuestionAnswerNotFoundException;
import fr.acajou.quiz.exception.QuestionNotFoundException;
import fr.acajou.quiz.repository.IQuestionAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionAnswerService {

    private final IQuestionAnswerRepository questionAnswerRepository;
    private final QuestionAnswerMapper questionAnswerMapper;

    private final QuestionService questionService;
    private final AnswerService answerService;


    public QuestionAnswerDTO createQuestionAnswer(QuestionAnswerDTO questionAnswerDTO) {

        Question question = questionService.getbyUUID(questionAnswerDTO.question());
        Answer answer = answerService.getbyUUID(questionAnswerDTO.answer());

        QuestionAnswer questionAnswer = new QuestionAnswer(null,null,question,answer,questionAnswerDTO.correct());
        QuestionAnswer savedQuestionAnswer = questionAnswerRepository.save(questionAnswer);
        return questionAnswerMapper.entityToDto(savedQuestionAnswer);
    }

    public QuestionAnswerDTO updateQuestionAnswer(QuestionAnswerDTO questionAnswerDTO) {

        Long id = getId(questionAnswerDTO);
        
        Question question = questionService.getbyUUID(questionAnswerDTO.question());
        Answer answer = answerService.getbyUUID(questionAnswerDTO.answer());

        QuestionAnswer updateQuestionAnswer = new QuestionAnswer(id,questionAnswerDTO.uuid(),question,answer,questionAnswerDTO.correct());
        QuestionAnswer savedQuestionAnswer = questionAnswerRepository.save(updateQuestionAnswer);
        return questionAnswerMapper.entityToDto(savedQuestionAnswer);
    }

    public QuestionAnswerDTO getQuestionAnswerbyUUID(UUID uuid) {
        Optional<QuestionAnswer> questionAnswer = questionAnswerRepository.findByUuid(uuid);
        return questionAnswer.map(questionAnswerMapper::entityToDto).orElseThrow(() -> new QuestionAnswerNotFoundException("Question Answer Not Found : L'uuid questionAnswer "+uuid+" n'a pas été trouvé"));
    }

    public void deleteQuestionAnswerbyUUID(UUID uuid) {
        Optional<QuestionAnswer> questionAnswer = questionAnswerRepository.findByUuid(uuid);
        QuestionAnswer questionAnswerSup = questionAnswer.orElseThrow(() -> new QuestionAnswerNotFoundException("Question Answer Not Found : L'uuid questionAnswer "+uuid+" n'a pas été trouvé"));
        questionAnswerRepository.delete(questionAnswerSup);
    }

    public Long getId(QuestionAnswerDTO questionAnswerDTO) {
        UUID uuid = questionAnswerDTO.uuid();
        QuestionAnswer questionAnswerEntity = questionAnswerRepository.findByUuid(uuid)
                .orElseThrow(() -> new QuestionNotFoundException("Question Not Found : L'uuid question " + uuid + " n'a pas été trouvé"));
        return questionAnswerEntity.getId();
    }

}