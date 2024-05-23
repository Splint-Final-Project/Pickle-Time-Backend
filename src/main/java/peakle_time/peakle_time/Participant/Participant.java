package peakle_time.peakle_time.Participant;


import jakarta.persistence.*;
import lombok.Getter;
import peakle_time.peakle_time.global.BaseEntity;
import peakle_time.peakle_time.Member.Member;
import peakle_time.peakle_time.Study.Study;

@Entity
@Getter
public class Participant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "studyId")
    private Study study;

    @Column(name = "role")
    private String role;

    public Participant() {}

    public Participant(Member member, Study study, String role) {
        this.member = member;
        this.study = study;
        this.role = role;
    }
}
