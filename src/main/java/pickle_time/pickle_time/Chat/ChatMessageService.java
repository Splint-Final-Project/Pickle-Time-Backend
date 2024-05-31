package pickle_time.pickle_time.Chat;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pickle_time.pickle_time.ChatRoom.ChatRoom;
import pickle_time.pickle_time.ChatRoom.ChatRoomRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private static final Logger logger = LoggerFactory.getLogger(ChatMessageService.class);

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private ChatMessageRepository messageRepository;
//    private UserRepository userRepository;

    public void sendMessage(String senderId, String receiverId, String message) {
        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findByParticipants(senderId, receiverId);

        ChatRoom chatRoom;
        if (optionalChatRoom.isPresent()) {
            chatRoom = optionalChatRoom.get();
        } else {
            chatRoom = new ChatRoom(List.of(senderId, receiverId));
            chatRoomRepository.save(chatRoom);
        }

        ChatMessage newMessage = ChatMessage.builder()
                .senderId(senderId)
                .receiverId(receiverId)
                .message(message)
                .build();

        messageRepository.save(newMessage);

        chatRoom.getMessages().add(newMessage.getId());
        chatRoomRepository.save(chatRoom);
    }

//    public List<ChatMessage> getMessages(String senderId, String userToChatId) {
//        User sender = userRepository.findById(senderId).orElseThrow(() -> new IllegalArgumentException("Sender not found"));
//        User receiver = userRepository.findById(userToChatId).orElseThrow(() -> new IllegalArgumentException("Receiver not found"));
//
//        return chatRoomRepository.findByParticipants(List.of(senderId, userToChatId))
//                .map(ChatRoom::getMessages)
//                .orElse(List.of());
//    }
}
