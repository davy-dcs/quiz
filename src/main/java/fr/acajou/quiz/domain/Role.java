package fr.acajou.quiz.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Role  implements GrantedAuthority {

    @NotBlank
    @Size(min = 5, max = 20)
    @Id
    @Column(name = "name", length = 20)
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
