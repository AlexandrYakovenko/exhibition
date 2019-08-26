package ua.yakovenko.domain;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor()
@Entity
public class Exhibition {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String showroom;

}
