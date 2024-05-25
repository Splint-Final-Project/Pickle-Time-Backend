package pickle_time.pickle_time.Member;

import jakarta.persistence.*;
import lombok.*;
import pickle_time.pickle_time.global.BaseEntity;
import pickle_time.pickle_time.Participant.Participant;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @Column(nullable = false)
    private String nickname;

    private String password;

    private String image;

    private String company;
    private String socialType;
    private String imageUrl;


    @OneToMany
    @JoinColumn(name = "memberId")
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
