package fr.acajou.quiz.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "QUESTION_ANSWER")
public class QuestionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uuid = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "id_question", nullable = false)
    private Question question;

    @ManyToOne
    @JoinColumn(name = "id_answer", nullable = false)
    private Answer answer;

    private boolean correct;

    @PrePersist
    public void prePersist() {
        this.uuid = UUID.randomUUID();
    }
}
