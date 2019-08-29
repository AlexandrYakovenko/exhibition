package ua.yakovenko.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Exhibition {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NonNull
    @NotBlank(message = "Please, fill this field")
    @Length(max = 255, message = "Description too long (more than 255)")
    private String name;

    @NonNull
    @NotBlank(message = "Please, fill this field")
    @Length(max = 255, message = "Description too long (more than 255)")
    private String showroom;

    @NonNull
    @NotBlank(message = "Please, fill this field")
    @Length(max = 2048, message = "Description too long (more than 2kB)")
    private String description;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public String getAuthorName() {
        return author != null ? author.getUsername() : "admin";
    }

}
