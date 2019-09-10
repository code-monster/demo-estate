package ua.com.lineup.realestate.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@Data
public class ApartmentRequest implements Serializable {

    private Long id;

    @NotBlank
    private String address;

    private int rooms;

    private Long ownerId;

}
