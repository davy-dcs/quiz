package fr.acajou.quiz.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Play {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score;

    @ManyToOne
    @JoinColumn(name = "users")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "session")
    private Session session;


    @PrePersist
    public void prePersist() {
        this.score = 0;
    }

}
