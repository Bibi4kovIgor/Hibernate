package dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GreetingDto {
    private String identifier;

    private String greeting;

    private String target;

}
