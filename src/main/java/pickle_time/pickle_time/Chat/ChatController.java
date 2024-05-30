//package pickle_time.pickle_time.Chat;
//
//import jakarta.persistence.Id;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import pickle_time.pickle_time.User.User;
////import pickle_time.pickle_time.User.UserController;
//import pickle_time.pickle_time.User.UserRepository;
//
//import java.util.List;
//
//@Controller
//@RequiredArgsConstructor
//@RequestMapping("/api/messages")
//public class ChatController {
//
////    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
//    private SimpMessagingTemplate simpMessagingTemplate;
//
//    @Autowired
//    private ChatMessageService chatMessageService;
//
//    @Autowired
//    private UserRepository userRepository;
//
////    @PostMapping("/send/{id}")
////    public ResponseEntity<ChatMessage> sendMessage(@PathVariable("id") String receiverId, @RequestBody ChatMessage chatMessage) {
////        try {
////            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////            String senderId = authentication.getName(); // Assuming the user ID is stored as the username
////
////            // 메시지를 보내는 서비스 로직을 호출
////            ChatMessage message = chatMessageService.sendMessage(senderId, receiverId, chatMessage.getContent());
////
////            // STOMP를 통해 수신자에게 메시지 전달
//////            simpMessagingTemplate.convertAndSendToUser(receiverId, "/queue/messages", message);
////
////            return ResponseEntity.status(201).body(message);
////        } catch (Exception e) {
////            logger.error("Error during signup", e);
////            return ResponseEntity.status(500).body(null);
////        }
////    }
//@PostMapping("/send/{id}")
//public ResponseEntity<String> sendMessage(@PathVariable("id") String receiverId, @RequestBody ChatMessage chatMessage) {
//    try {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = (User) principal;
////        user.getId();
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////        String senderId = authentication.getName(); // Assuming the user ID is stored as the username
//
////        User sender = userRepository.findByIdNot(senderId);
////        User receiver = userRepository.findByIdNot(receiverId);
//
////        ChatMessage message = chatMessageService.sendMessage(senderId, receiverId, chatMessage.getContent());
//
//        // Notify the receiver via STOMP
////        simpMessagingTemplate.convertAndSendToUser(receiverId, "/queue/messages", message);
//
//        return ResponseEntity.status(201).body( user.getId());
//    } catch (Exception e) {
////        logger.error("Error during signup", e);
//        return ResponseEntity.status(500).body(null);
//    }
//}
//
//    @GetMapping("/{id}")
//    public ResponseEntity<List<ChatMessage>> getMessages(@PathVariable("id") String userToChatId) {
//        try {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            String senderId = authentication.getName(); // Assuming the user ID is stored as the username
//
//            // 메시지를 받는 서비스 로직을 호출
//            List<ChatMessage> messages = chatMessageService.getMessages(senderId, userToChatId);
//
//            return ResponseEntity.ok(messages);
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body(null);
//        }
//    }
//
////    @MessageMapping("/chat")
////    public void processMessage(@Payload ChatMessage chatMessage) {
////        ChatMessage savedMsg = chatMessageService.save(chatMessage);
////        messagingTemplate.convertAndSendToUser(
////                chatMessage.getRecipientId(), "/queue/messages",
////                new ChatNotification(
////                        savedMsg.getId(),
////                        savedMsg.getSenderId(),
////                        savedMsg.getRecipientId(),
////                        savedMsg.getContent()
////                )
////        );
////    }
////
////    @GetMapping("/messages/{senderId}/{recipientId}")
////    public ResponseEntity<List<ChatMessage>> findChatMessages(@PathVariable String senderId,
////                                                              @PathVariable String recipientId) {
////        return ResponseEntity
////                .ok(chatMessageService.findChatMessages(senderId, recipientId));
////    }
//}
