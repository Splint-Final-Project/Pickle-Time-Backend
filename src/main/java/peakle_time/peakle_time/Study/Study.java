package peakle_time.peakle_time.Study;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import peakle_time.peakle_time.Participant.Participant;
import peakle_time.peakle_time.global.BaseEntity;
import peakle_time.peakle_time.Member.Member;
import peakle_time.peakle_time.global.Location;

import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Study extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @OneToMany
    @JoinColumn(name = "studyId")
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

    public Study(Member member, String title, String content, double latitude, double longitude, Status status,Location location) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.viewCount = 0;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
        this.location = location;
    }
}
