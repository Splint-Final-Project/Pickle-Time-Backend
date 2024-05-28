package pickle_time.pickle_time.User;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User,String> {
//    List<User> findAllByStatus(Status status);
    List<User> findByIdNot(String id);
    User findByUsername(String username);
}
