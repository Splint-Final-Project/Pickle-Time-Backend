package pickle_time.pickle_time.User;

public enum Role {
    ROLE_USER("ROLE_USER"),
    ROLE_PENDING("ROLE_PENDING");
    private String role;

    Role(String role) {
        this.role = role;
    }
}
