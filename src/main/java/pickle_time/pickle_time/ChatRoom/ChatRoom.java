package pickle_time.pickle_time.ChatRoom;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import pickle_time.pickle_time.Chat.ChatMessage;
import pickle_time.pickle_time.User.User;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "conversations")
public class ChatRoom {

    @Id
    private String id;

    @DBRef
    private List<String> participants;

    @DBRef
    private List<ChatMessage> messages;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    // List<User>를 인자로 받는 생성자 추가
    public ChatRoom(List<String> participants) {
        this.participants = participants;
    }
}
