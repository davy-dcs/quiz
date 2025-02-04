package fr.acajou.quiz.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uuid;

    private Date date;

    private Integer timer;

    @ManyToOne
    @JoinColumn(name = "quiz")
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "users")
    private Users users;


    @PrePersist
    public void prePersist () {
        this.uuid = UUID.randomUUID();
        this.date = Date.from(Instant.now());
    }
}
