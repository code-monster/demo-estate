package ua.com.lineup.realestate.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.lineup.realestate.model.enumeration.OfferType;

import javax.persistence.*;


@Entity
@Table(name = "offer")
@Getter
@Setter
@NoArgsConstructor
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Apartment apartment;

    private double price;

    private OfferType offerType;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private User realtor;

    @ManyToOne(fetch = FetchType.EAGER)
    private User client;

    private boolean finished;

    public Offer(Apartment apartment, double price, OfferType offerType, String description, User realtor, User client, boolean finished) {
        this.apartment = apartment;
        this.price = price;
        this.offerType = offerType;
        this.description = description;
        this.realtor = realtor;
        this.client = client;
        this.finished = finished;
    }
}
