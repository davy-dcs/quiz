package fr.acajou.quiz.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
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
    @Cascade(CascadeType.MERGE)
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "users")
    @Cascade(CascadeType.MERGE)
    private Users user;


    @PrePersist
    public void prePersist () {
        this.uuid = UUID.randomUUID();
        this.date = Date.from(Instant.now());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(timer, session.timer) && Objects.equals(quiz, session.quiz) && Objects.equals(user, session.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timer, quiz, user);
    }
}
