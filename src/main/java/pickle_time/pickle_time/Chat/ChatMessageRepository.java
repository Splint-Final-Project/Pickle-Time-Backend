package pickle_time.pickle_time.Chat;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    Optional<ChatMessage> findChatMessageById(String id);

    Page<ChatMessage> findAllByIdIn(List<String> ids, Pageable pageable);
}