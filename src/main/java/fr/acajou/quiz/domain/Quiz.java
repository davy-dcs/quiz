package fr.acajou.quiz.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Quiz {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return Objects.equals(numberOfQuestions, quiz.numberOfQuestions) && category == quiz.category && difficulty == quiz.difficulty;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfQuestions, category, difficulty);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uuid;

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private Integer numberOfQuestions;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @OneToMany
    private List<Question> questions;

    @PrePersist
    void prePersist() {
        uuid = UUID.randomUUID();
    }
}
