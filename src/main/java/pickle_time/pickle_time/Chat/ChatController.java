package pickle_time.pickle_time.Chat;

import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pickle_time.pickle_time.User.Repository.UserRepository;
import pickle_time.pickle_time.User.model.Users;
import pickle_time.pickle_time.global.dto.ApiResponse;
//import pickle_time.pickle_time.User.UserController;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/messages")
public class ChatController {

//    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private ChatMessageService chatMessageService;

//    @PostMapping("/send/{id}")
//    public ResponseEntity<ChatMessage> sendMessage(@PathVariable("id") String receiverId, @RequestBody ChatMessage chatMessage) {
//        try {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            String senderId = authentication.getName(); // Assuming the user ID is stored as the username
//
//            // 메시지를 보내는 서비스 로직을 호출
//            ChatMessage message = chatMessageService.sendMessage(senderId, receiverId, chatMessage.getContent());
//
//            // STOMP를 통해 수신자에게 메시지 전달
////            simpMessagingTemplate.convertAndSendToUser(receiverId, "/queue/messages", message);
//
//            return ResponseEntity.status(201).body(message);
//        } catch (Exception e) {
//            logger.error("Error during signup", e);
//            return ResponseEntity.status(500).body(null);
//        }
//    }

    @PostMapping("/send/{id}")
    public ResponseEntity<String> sendMessage(@PathVariable("id") String receiverId, @RequestBody ChatMessage chatMessage) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails user = (UserDetails) authentication.getPrincipal();
            Long userId = Long.parseLong(user.getUsername());
//            Long longReceiverId = Long.parseLong(receiverId);
//
//            Optional<Users> senderOptional = userRepository.findById(userId);
//            Optional<Users> receiverOptional = userRepository.findById(longReceiverId);
//
//            if (senderOptional.isPresent() && receiverOptional.isPresent()) {
//                Users sender = senderOptional.get();
//                Users receiver = receiverOptional.get();
//                // sender와 receiver를 사용하여 로직을 구현하세요.
//            } else {
//                // sender 또는 receiver 중 하나라도 존재하지 않는 경우에 대한 처리를 여기에 구현하세요.
//                if (!senderOptional.isPresent()) {
//                    // sender가 존재하지 않는 경우
//                    System.out.println("Sender not found with id: " + userId);
//                }
//                if (!receiverOptional.isPresent()) {
//                    // receiver가 존재하지 않는 경우
//                    System.out.println("Receiver not found with id: " + receiverId);
//                }
//            }

            chatMessageService.sendMessage(user.getUsername(), receiverId, chatMessage.getMessage());

            // Notify the receiver via STOMP
    //        simpMessagingTemplate.convertAndSendToUser(receiverId, "/queue/messages", message);
            return ResponseEntity.status(201).body("ok");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<List<ChatMessage>>> getMessages(@PathVariable("id") String userToChatId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails user = (UserDetails) authentication.getPrincipal(); // Assuming the user ID is stored as the username

            // 메시지를 받는 서비스 로직을 호출
            Page<ChatMessage> chatMessagesList = chatMessageService.getMessages(user.getUsername(), userToChatId);
            System.out.println(chatMessagesList);
            return ResponseEntity.ok(new ApiResponse<>(true, chatMessagesList.getContent(), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

//    @MessageMapping("/chat")
//    public void processMessage(@Payload ChatMessage chatMessage) {
//        ChatMessage savedMsg = chatMessageService.save(chatMessage);
//        messagingTemplate.convertAndSendToUser(
//                chatMessage.getRecipientId(), "/queue/messages",
//                new ChatNotification(
//                        savedMsg.getId(),
//                        savedMsg.getSenderId(),
//                        savedMsg.getRecipientId(),
//                        savedMsg.getContent()
//                )
//        );
//    }
//
//    @GetMapping("/messages/{senderId}/{recipientId}")
//    public ResponseEntity<List<ChatMessage>> findChatMessages(@PathVariable String senderId,
//                                                              @PathVariable String recipientId) {
//        return ResponseEntity
//                .ok(chatMessageService.findChatMessages(senderId, recipientId));
//    }
}
