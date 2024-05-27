package pickle_time.pickle_time.Pickle;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pickle_time.pickle_time.User.Users;
import pickle_time.pickle_time.Participant.Participant;


import pickle_time.pickle_time.global.entity.BaseEntity;
import pickle_time.pickle_time.global.entity.Location;

import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pickle extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usersId")
    private Users users;

    @OneToMany
    @JoinColumn(name = "pickleId")
    private List<Participant> participants;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "viewCount")
    private int viewCount;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Embedded
    private Location location;

    public Pickle(Users users, String title, String content, double latitude, double longitude, Status status, Location location) {
        this.users = users;
        this.title = title;
        this.content = content;
        this.viewCount = 0;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
        this.location = location;
    }
}
