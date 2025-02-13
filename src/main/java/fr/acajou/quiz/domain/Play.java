package fr.acajou.quiz.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

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

    private UUID uuid;

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
        this.uuid = UUID.randomUUID();
    }

}
