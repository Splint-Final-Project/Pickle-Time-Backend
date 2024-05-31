package pickle_time.pickle_time.Chat;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    Optional<ChatMessage> findChatMessageById(String id);
}