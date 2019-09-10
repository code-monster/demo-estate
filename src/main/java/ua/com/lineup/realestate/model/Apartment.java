package ua.com.lineup.realestate.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "apartment")
@Getter
@Setter
@NoArgsConstructor
public class Apartment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String address;

    private int rooms;

    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;

    public Apartment(@NotBlank String address, int rooms, User owner) {
        this.address = address;
        this.rooms = rooms;
        this.owner = owner;
    }
}
