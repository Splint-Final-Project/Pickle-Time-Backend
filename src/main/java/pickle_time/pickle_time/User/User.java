package pickle_time.pickle_time.User;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "users") // 컬렉션 이름 지정
public class User {
//    @Id
//    private String nickName;
//    private String fullName;
//    private Status status;

    @Id
    private String id;

//    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Username is required")
    @Size(min = 1, message = "Username must be unique")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Gender is required")
    private String gender;

    private String profilePic = "";

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
    private Status status;

    public User(String fullName, String username, String password, String gender, String profilePic) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.profilePic = profilePic;
    }
}