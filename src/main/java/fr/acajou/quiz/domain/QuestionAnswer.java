package fr.acajou.quiz.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "QUESTION_ANSWER")
public class QuestionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_question", nullable = false)
    private Question question;

    @ManyToOne
    @JoinColumn(name = "id_answer", nullable = false)
    private Answer answer;

    private boolean correct = false;
}
