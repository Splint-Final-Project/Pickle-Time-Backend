package pickle_time.pickle_time.Participant;


import jakarta.persistence.*;
import lombok.Getter;
import pickle_time.pickle_time.global.BaseEntity;
import pickle_time.pickle_time.User.Users;
import pickle_time.pickle_time.Pickle.Pickle;

@Entity
@Getter
public class Participant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "pickleId")
    private Pickle pickle;

    @Column(name = "role")
    private String role;

    public Participant() {}

    public Participant(Users users, Pickle pickle, String role) {
        this.users = users;
        this.pickle = pickle;
        this.role = role;
    }
}
