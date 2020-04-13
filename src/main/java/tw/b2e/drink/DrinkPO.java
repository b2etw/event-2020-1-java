package tw.b2e.drink;

import lombok.Data;

@Data
public class DrinkPO {
    private String name;
    private String price;
    private Integer sugar;
    private Integer temperature;
}
