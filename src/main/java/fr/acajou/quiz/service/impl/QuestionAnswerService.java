package fr.acajou.quiz.service.impl;

import fr.acajou.quiz.domain.Question;
import fr.acajou.quiz.domain.QuestionAnswer;
import fr.acajou.quiz.dto.QuestionAnswerDTO;
import fr.acajou.quiz.dto.QuestionDTO;
import fr.acajou.quiz.dto.mapper.QuestionAnswerMapper;
import fr.acajou.quiz.repository.IQuestionAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionAnswerService {

    private final IQuestionAnswerRepository questionAnswerRepository;
    private final QuestionAnswerMapper questionAnswerMapper;

    /*
    public QuestionAnswerDTO createQuestionAnswer(QuestionAnswerDTO questionAnswerDTO) {

        QuestionAnswer questionAnswer = new QuestionAnswer(null,null,Q,R,questionAnswerDTO.correct());
        QuestionAnswer savedQuestion = questionAnswerRepository.save(questionAnswer);
        return questionAnswerMapper.entityToDto(savedQuestion);
    }
    */
}
