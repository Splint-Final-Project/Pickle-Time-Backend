package pickle_time.pickle_time.User;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document
public class User {
    @Id
    private String nickName;
    private String fullName;
    private Status status;

//    @Id
//    private String id;
//
//    @NotBlank(message = "Full name is required")
//    private String fullName;
//
//    @NotBlank(message = "Username is required")
//    @Size(min = 1, message = "Username must be unique")
//    private String username;
//
//    @NotBlank(message = "Password is required")
//    @Size(min = 6, message = "Password must be at least 6 characters")
//    private String password;
//
//    @NotBlank(message = "Gender is required")
//    private String gender;
//
//    private String profilePic = "";
//
//    @CreatedDate
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate
//    private LocalDateTime updatedAt;
//    private Status status;
}