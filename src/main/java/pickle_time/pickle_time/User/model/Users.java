package pickle_time.pickle_time.User.model;

import jakarta.persistence.*;
import lombok.*;
import pickle_time.pickle_time.Participant.model.Participant;
import pickle_time.pickle_time.Scrap.model.Scrap;
import pickle_time.pickle_time.User.Role;
import pickle_time.pickle_time.global.auth.oauth.ProviderType;
import pickle_time.pickle_time.global.entity.BaseEntity;

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

    @Column
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Role role;


    @Column(name = "company")
    private String company;

    @Column(name = "socialType")
    private ProviderType providerType;

    @Column(name = "imageUrl")
    private String imageUrl;


    @OneToMany
    @JoinColumn(name = "usersId")
    private List<Participant> participants;

    @OneToMany
    @JoinColumn(name = "userId")
    private List<Scrap> scraps;


    public void update(String nickname, String company,String imageUrl) {

        if (nickname != null) {
            System.out.println(nickname);
            this.nickname = nickname;
        }
        if (company != null) {
            System.out.println(company);
            this.company = company;
        }
        if (imageUrl != null) {
            System.out.println(imageUrl);
            this.imageUrl  = imageUrl;
        }
    }




    public Users(String email, String nickname,  ProviderType providerType, String imageUrl) {
        this.email = email;
        this.nickname = nickname;
        this.providerType = providerType;
        this.imageUrl = imageUrl;
        this.role = Role.ROLE_PENDING;
    }

}


