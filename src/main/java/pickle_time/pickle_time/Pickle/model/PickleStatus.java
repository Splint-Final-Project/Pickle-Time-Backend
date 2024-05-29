package pickle_time.pickle_time.Pickle.model;

public enum PickleStatus {

    RECRUITING("모집 중"),
    Start("시작"),
    End("완료");

    private final String status;

    PickleStatus(String status){
        this.status = status;
    }

}
