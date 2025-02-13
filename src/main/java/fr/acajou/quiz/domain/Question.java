package fr.acajou.quiz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.aspectj.weaver.patterns.TypeCategoryTypePattern;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "QUESTION")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uuid;

    @NotBlank(message = "Le champs value de la question dans QUESTION est obligatoire")
    private String value;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
//TODO demander a steve pour list answer
    @Enumerated(EnumType.STRING)
    private List<Category> categories;

    @PrePersist
    public void prePersist() {
        this.uuid = UUID.randomUUID();
    }
}
