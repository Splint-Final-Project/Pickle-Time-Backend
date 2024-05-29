package pickle_time.pickle_time.User.model;

import jakarta.persistence.*;
import lombok.*;
import pickle_time.pickle_time.global.entity.BaseEntity;
import pickle_time.pickle_time.Participant.model.Participant;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "company")
    private String company;

    @Column(name = "socialType")
    private String socialType;

    @Column(name = "imageUrl")
    private String imageUrl;


    @OneToMany
    @JoinColumn(name = "usersId")
    private List<Participant> participants;

    public void update(String nickname, String email, String company,String imageUrl) {
        if (nickname != null) {
            this.nickname = nickname;
        }
        if (email != null) {
            this.email = email;
        }
        if (company != null) {
            this.company = company;
        }
        if (imageUrl != null) {
            this.imageUrl = imageUrl;
        }
    }

}