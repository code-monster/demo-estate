package ua.com.lineup.realestate.model.request;

import lombok.Data;
import ua.com.lineup.realestate.model.enumeration.ImpressionResult;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public class ImpressionRequest implements Serializable {

    private Long id;

    @NotBlank
    @NotNull
    private Long apartmentId;

    @NotBlank
    @NotNull
    private Long managerId;

    @NotBlank
    @NotNull
    private LocalDateTime dateImpression;

    @NotBlank
    @NotNull
    private ImpressionResult result;

}
