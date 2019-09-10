package ua.com.lineup.realestate.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.lineup.realestate.model.enumeration.ImpressionResult;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "impression")
@Getter
@Setter
@NoArgsConstructor
public class Impression implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Apartment apartment;

    @ManyToOne(fetch = FetchType.EAGER)
    private User manager;

    private LocalDateTime dateCreated;

    private LocalDateTime dateImpression;

    private ImpressionResult result;

    public Impression(Apartment apartment, User manager, LocalDateTime dateCreated, LocalDateTime dateImpression, ImpressionResult result) {
        this.apartment = apartment;
        this.manager = manager;
        this.dateCreated = dateCreated;
        this.dateImpression = dateImpression;
        this.result = result;
    }
}
