package JavaEE7.Validation.domain;

import lombok.*;

import javax.validation.constraints.*;

/**
 * Created by alex on 10/29/15.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @NotNull(message = "Street name may not be null")
    @Size(min = 3, max = 250, message = "Streetname must be between 3 and 250 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Streetname can only contain letters")
    private String streetName;

    @Size(min=0 , max = 5, message = "Appendix must be less than 5 characters.")
    private String appendix;

    @NotNull(message = "Zip code may not be null")
    @Pattern(regexp = "^[1-9][0-9]{3}\\s?[a-zA-Z]{2}$", message = "Zip code is not in the correct pattern 0000AA")
    private String zipCode;

    @NotNull(message = "City may not be null")
    @Size(min=3, max=250, message = "City must be between 3 and 250 characters in length")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "City must only contain letters.")
    private String city;

    @NotNull(message = "Number may not be null")
    @Min(value = 1, message = "Number must be larger than one")
    private int number;
}
