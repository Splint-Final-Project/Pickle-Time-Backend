package peakle_time.peakle_time.Member;

import jakarta.persistence.*;
import lombok.*;
import peakle_time.peakle_time.global.BaseEntity;
import peakle_time.peakle_time.Image.Image;
import peakle_time.peakle_time.global.Location;
import peakle_time.peakle_time.Participant.Participant;

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

    private String loginId;

    @Column(nullable = false)
    private String nickname;

    private String email;
    private String password;

    @OneToOne
    private Image image;

//    @Embedded
//    private Location location;
    private String company;
    private String socialType;


    @OneToMany
    @JoinColumn(name = "memberId")
    private List<Participant> participants;

    public void update(String nickname, String email, String company) {
        if (nickname != null) {
            this.nickname = nickname;
        }
        if (email != null) {
            this.email = email;
        }
        if (company != null) {
            this.company = company;
        }
    }

}
