package fr.acajou.quiz.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class QuizQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quiz")
    private Quiz quiz;

    //TODO Replace Object by Question
    @ManyToOne
    @JoinColumn(name = "question")
    private Object question;
}
