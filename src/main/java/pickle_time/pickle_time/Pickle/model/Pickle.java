package pickle_time.pickle_time.Pickle.model;

import jakarta.persistence.*;
import lombok.*;
import pickle_time.pickle_time.User.model.Users;
import pickle_time.pickle_time.Participant.model.Participant;
import pickle_time.pickle_time.global.entity.BaseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Pickle extends BaseEntity {

    private static final Integer MAX_LATITUDE = 90;
    private static final Integer MIN_LATITUDE = -90;
    private static final Integer MAX_LONGITUDE = 180;
    private static final Integer MIN_LONGITUDE = -180;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;

    @OneToMany
    @JoinColumn(name = "pickleId")
    private List<Participant> participants = new ArrayList<>(); // 피클에 참여하는 참여자

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "viewCount")
    private Integer viewCount;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "capacity")
    private Integer capacity;   // 인원

    @Column(name = "pickleType")
    @Enumerated(EnumType.STRING)
    private PickleType pickleType;

    @Column(name = "startDate")
    private LocalDate startDate;

    @Column(name = "endDate")
    private LocalDate endDate;

    @Column(name = "price")
    private Integer price;

    @Column(name = "auth")
    private Integer auth;

    @Column(name = "category")
    private String category;

    @Column(name = "pickleStatus")
    @Enumerated(EnumType.STRING)
    private PickleStatus pickleStatus;

    public void pickleUpdate(String title, String content, double latitude, double longitude,
                       PickleType pickleType, LocalDate startDate, LocalDate endDate, Integer price,
                       Integer auth, String category, Integer capacity) {
        if (title != null) {
            this.title = title;
        }
        if (content != null) {
            this.content = content;
        }
        if (latitude >= MIN_LATITUDE && latitude <= MAX_LATITUDE) {
            this.latitude = latitude;
        }
        if (longitude >= MIN_LONGITUDE && longitude <= MAX_LONGITUDE) {
            this.longitude = longitude;
        }
        if (pickleType != null) {
            this.pickleType = pickleType;
        }
        if (startDate != null) {
            this.startDate = startDate;
        }
        if (endDate != null) {
            this.endDate = endDate;
        }
        if (price != null) {
            this.price = price;
        }
        if (auth != null) {
            this.auth = auth;
        }
        if (category != null) {
            this.category = category;
        }
        if (capacity != null) {
            this.capacity = capacity;
        }
    }


    public void changeStatus(PickleStatus pickleStatus) {
        this.pickleStatus = pickleStatus;
    }
}
