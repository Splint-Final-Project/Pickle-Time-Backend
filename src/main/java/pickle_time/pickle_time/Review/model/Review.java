package pickle_time.pickle_time.Review.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pickle_time.pickle_time.User.model.Users;
import pickle_time.pickle_time.Pickle.model.Pickle;
import pickle_time.pickle_time.global.entity.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "pickle"})
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "record", nullable = false)
    private String record;

    @Column(name = "star", nullable = false)
    private int star;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pickleId")
    private Pickle pickle;

    public Review(String record, int star, Users user, Pickle pickle) {
        this.record = record;
        this.star = star;
        this.user = user;
        this.pickle = pickle;
    }
}
