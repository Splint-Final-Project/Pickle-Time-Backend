package pickle_time.pickle_time.ChatRoom;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import pickle_time.pickle_time.Chat.ChatMessage;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    private List<String> participants;

    private List<String> messages = new ArrayList<>(); //

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    // List<User>를 인자로 받는 생성자 추가
    public ChatRoom(List<String> participants) {
        this.participants = participants;
    }
}
