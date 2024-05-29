package pickle_time.pickle_time.Chat;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
//    List<ChatMessage> findByChatId(String chatId);
}
