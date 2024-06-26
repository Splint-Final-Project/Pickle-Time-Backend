package pickle_time.pickle_time.Chat;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import pickle_time.pickle_time.ChatRoom.ChatRoom;

import java.time.LocalDateTime;

@EnableMongoAuditing
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "messages")
public class ChatMessage {

    @Id
    private String id;

    @NotNull
    private String senderId;

    @NotNull
    private String receiverId;

    @NotBlank
    private String message; // 필드 이름을 content로 변경

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


//    public ChatMessage(String senderId, String receiverId) {
//        this.senderId = senderId;
//        this.receiverId = receiverId;
//    }
}
