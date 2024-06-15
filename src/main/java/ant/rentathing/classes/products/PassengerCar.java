package ant.rentathing.classes.products;

import ant.rentathing.classes.User;
import ant.rentathing.classes.singleton.ProductList;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public class PassengerCar extends Product {
    private String brand;
    private double weight;
    private int engineCapacity;

    public PassengerCar(String description, String brand, double weight, int engineCapacity) throws IOException {
        super("PassengerCar", description);
        this.brand = brand;
        this.weight = weight;
        this.engineCapacity = engineCapacity;
        ProductList.getInstance().addProduct(this);
    }

    @JsonCreator
    public PassengerCar(@JsonProperty("id") String id,
                        @JsonProperty("description") String description,
                        @JsonProperty("availability") boolean availability,
                        @JsonProperty("customer") String customer,
                        @JsonProperty("employee") User employee,
                        @JsonProperty("insured") boolean insured,
                        @JsonProperty("days") int days,
                        @JsonProperty("brand") String brand,
                        @JsonProperty("weight") double weight,
                        @JsonProperty("engineCapacity") int engineCapacity) {
        super(id, "PassengerCar", description, availability, customer, employee, insured, days);
        this.brand = brand;
        this.weight = weight;
        this.engineCapacity = engineCapacity;
    }

    @Override
    protected double calculatePricePerDay() {
        return 50;
    }

    @Override
    protected double calculateInsurancePerDay() {
        return 0.01 * engineCapacity;
    }
}
