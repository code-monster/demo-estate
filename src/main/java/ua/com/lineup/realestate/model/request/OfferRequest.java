package ua.com.lineup.realestate.model.request;

import lombok.Data;
import ua.com.lineup.realestate.model.enumeration.OfferType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class OfferRequest implements Serializable {

    private Long id;

    @NotBlank
    @NotNull
    private Long apartmentId;

    @NotBlank
    private double price;

    @NotBlank
    @NotNull
    private OfferType offerType;

    private String description;

    private Long realtorId;

    private Long clientId;

    private boolean finished;

}
