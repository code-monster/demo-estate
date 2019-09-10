package ua.com.lineup.realestate.model.responce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserSummaryResponse {
    private Long id;
    private String name;
    private String email;
}