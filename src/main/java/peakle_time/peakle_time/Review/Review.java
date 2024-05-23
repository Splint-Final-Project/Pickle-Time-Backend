package peakle_time.peakle_time.Review;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import peakle_time.peakle_time.global.BaseEntity;

public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "record")
    private String record;

    @Column(name = "star")
    private int star;
}
