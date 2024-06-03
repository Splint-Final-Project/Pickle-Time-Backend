//package pickle_time.pickle_time.conversation;
//
//import jakarta.persistence.*;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import pickle_time.pickle_time.Message.Message;
//
//import java.util.List;
//
//@Entity
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class Conversation {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToOne
//    @JoinColumn(name = "pickleId")
//    private Pickle pickle;
//
//    @OneToMany
//    @JoinColumn(name = "messageId")
//    private List<Message> messages;
//}
