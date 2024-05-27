package pickle_time.pickle_time.conversation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pickle_time.pickle_time.Pickle.Pickle;
import pickle_time.pickle_time.Pickle.PickleRepository;

import java.util.Optional;

@Service
public class ConversationService {

    @Autowired
    private PickleRepository pickleRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @Transactional
    public Conversation saveConversation(Conversation conversation) {
        return conversationRepository.save(conversation);
    }
}
