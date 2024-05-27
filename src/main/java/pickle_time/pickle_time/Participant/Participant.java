package pickle_time.pickle_time.Participant;


import jakarta.persistence.*;
import lombok.Getter;
import pickle_time.pickle_time.global.entity.BaseEntity;
import pickle_time.pickle_time.Member.Member;
import pickle_time.pickle_time.Pickle.Pickle;

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
    @JoinColumn(name = "pickleId")
    private Pickle pickle;

    @Column(name = "role")
    private String role;

    public Participant() {}

    public Participant(Member member, Pickle pickle, String role) {
        this.member = member;
        this.pickle = pickle;
        this.role = role;
    }
}
