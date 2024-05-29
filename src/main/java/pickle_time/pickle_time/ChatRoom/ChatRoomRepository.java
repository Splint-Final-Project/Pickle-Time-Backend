package pickle_time.pickle_time.ChatRoom;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {

    @Query("{ 'participants': { $all: [?0, ?1] } }")
    Optional<ChatRoom> findByParticipants(List<String> participantIds);

//    Optional<ChatRoom> findByParticipants(String senderId, String receiverId);
}
