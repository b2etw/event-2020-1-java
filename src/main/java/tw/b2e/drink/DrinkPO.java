package tw.b2e.drink;

public class DrinkPO {
    private String name;
    private String price;
    private Integer sugar;
    private Integer temperature;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getSugar() {
        return sugar;
    }

    public void setSugar(Integer sugar) {
        this.sugar = sugar;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "DrinkPO{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", sugar=" + sugar +
                ", temperature=" + temperature +
                '}';
    }

    public DrinkPO(String name, String price, Integer sugar, Integer temperature) {
        this.name = name;
        this.price = price;
        this.sugar = sugar;
        this.temperature = temperature;
    }
}
