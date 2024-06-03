package pickle_time.pickle_time.Pickle.model;

public enum PickleStatus {
    RECRUITING("모집 중"),
    START("시작"),
    END("완료");

    private final String status;

    PickleStatus(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
