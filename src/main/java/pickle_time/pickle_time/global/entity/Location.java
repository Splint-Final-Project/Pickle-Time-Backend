package pickle_time.pickle_time.global.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class Location {
    private String city;
    private String company;

    protected Location(final String city, final String company) {
        this.city = city;
        this.company = company;
    }

    public static Location of(final String city, final String company) {
        return new Location(city, company);
    }
}
