package mk.ukim.finki.wp.jan2025g2.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParkLocation {

    private Long id;
    private String country;
    private String continent;

    public ParkLocation(String country, String continent) {
        this.country = country;
        this.continent = continent;
    }
}
