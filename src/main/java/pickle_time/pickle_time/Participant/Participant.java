package pickle_time.pickle_time.Participant;


import jakarta.persistence.*;
import lombok.Getter;
import pickle_time.pickle_time.User.model.Users;
import pickle_time.pickle_time.Pickle.model.Pickle;
import pickle_time.pickle_time.global.entity.BaseEntity;

@Entity
@Getter
public class Participant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usersId")
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