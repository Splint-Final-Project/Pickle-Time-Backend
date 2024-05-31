package pickle_time.pickle_time.Chat;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pickle_time.pickle_time.ChatRoom.ChatRoom;
import pickle_time.pickle_time.ChatRoom.ChatRoomRepository;

import java.util.ArrayList;
import java.util.HashMap;
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

    public Page<ChatMessage> getMessages(String senderId, String receiverId) {
        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findByParticipants(senderId, receiverId);
        List<String> messagesIdsInChatRoom = optionalChatRoom.get().getMessages();

        Pageable pageable = PageRequest.of(0, 10);
        Page<ChatMessage> messagesWithPage = messageRepository.findAllByIdIn(messagesIdsInChatRoom, pageable);
        System.out.println(messagesWithPage.getContent());
//        List<ChatMessage> chatMessagesList = new ArrayList<>();
//        for (String messageId : messagesIdsInChatRoom) {
//            Optional<ChatMessage> optionalChatMessage = messageRepository.findChatMessageById(messageId);
//            optionalChatMessage.ifPresent(chatMessagesList::add);
//
////            return chatMessagesList;
//        }
        return messagesWithPage;
    }
}
