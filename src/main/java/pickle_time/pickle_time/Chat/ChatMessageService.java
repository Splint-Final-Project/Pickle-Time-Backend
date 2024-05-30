//package pickle_time.pickle_time.Chat;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import pickle_time.pickle_time.ChatRoom.ChatRoom;
//import pickle_time.pickle_time.ChatRoom.ChatRoomRepository;
//import pickle_time.pickle_time.User.User;
//import pickle_time.pickle_time.User.UserRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class ChatMessageService {
//
//    @Autowired
//    private ChatRoomRepository chatRoomRepository;
//    private ChatMessageRepository messageRepository;
//    private UserRepository userRepository;
//
//    public ChatMessage sendMessage(String senderId, String receiverId, String content) {
////        User sender = userRepository.findById(senderId).orElseThrow(() -> new IllegalArgumentException("Sender not found"));
////        User receiver = userRepository.findById(receiverId).orElseThrow(() -> new IllegalArgumentException("Receiver not found"));
//
//        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findByParticipants(List.of(senderId, receiverId));
//
//        ChatRoom chatRoom;
//        if (optionalChatRoom.isPresent()) {
//            chatRoom = optionalChatRoom.get();
//        } else {
//            chatRoom = new ChatRoom(List.of(senderId, receiverId));
//            chatRoomRepository.save(chatRoom);
//        }
//
//        ChatMessage newMessage = ChatMessage.builder()
//                .senderId(senderId)
//                .receiverId(receiverId)
//                .content(content)
//                .build();
//        messageRepository.save(newMessage);
//
//        chatRoom.getMessages().add(newMessage);
//        chatRoomRepository.save(chatRoom);
//
//        return newMessage;
//    }
//
//    public List<ChatMessage> getMessages(String senderId, String userToChatId) {
//        User sender = userRepository.findById(senderId).orElseThrow(() -> new IllegalArgumentException("Sender not found"));
//        User receiver = userRepository.findById(userToChatId).orElseThrow(() -> new IllegalArgumentException("Receiver not found"));
//
//        return chatRoomRepository.findByParticipants(List.of(senderId, userToChatId))
//                .map(ChatRoom::getMessages)
//                .orElse(List.of());
//    }
//}
