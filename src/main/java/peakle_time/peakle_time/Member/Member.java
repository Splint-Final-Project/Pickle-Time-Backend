package peakle_time.peakle_time.Member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import peakle_time.peakle_time.BaseEntity;
import peakle_time.peakle_time.Image.Image;
import peakle_time.peakle_time.Location;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    private String email;
    private String password;
    private Long age;

    @OneToOne
    private Image image;

    @Embedded
    private Location location;
    private String socialType;


}