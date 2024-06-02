package pickle_time.pickle_time.Scrap.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pickle_time.pickle_time.Pickle.model.Pickle;
import pickle_time.pickle_time.User.model.Users;
import pickle_time.pickle_time.global.entity.BaseEntity;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Scrap extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "pickleId")
    private Pickle pickle;
}
