package pickle_time.pickle_time.Pickle.model;

public enum PickleType {

    One("각자"), Many("함께");

    private final String pickleType;

    PickleType(String pickleType){
        this.pickleType = pickleType;
    }

}
