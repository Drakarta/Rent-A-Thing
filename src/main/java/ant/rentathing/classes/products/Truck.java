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
public class Truck extends Product {
    private int loadCapacity;
    private int engineCapacity;

    public Truck(String description, boolean availability, String customer, User employee, int loadCapacity, int engineCapacity) throws IOException {
        super("Truck", description, availability, customer, employee);
        this.loadCapacity = loadCapacity;
        this.engineCapacity = engineCapacity;
        ProductList.getInstance().addProduct(this);
    }

    @JsonCreator
    public Truck(@JsonProperty("id") String id,
                 @JsonProperty("description") String description,
                 @JsonProperty("availability") boolean availability,
                 @JsonProperty("customer") String customer,
                 @JsonProperty("employee") User employee,
                 @JsonProperty("insured") boolean insured,
                 @JsonProperty("days") int days,
                 @JsonProperty("loadCapacity") int loadCapacity,
                 @JsonProperty("engineCapacity") int engineCapacity) {
        super(id, "Truck", description, availability, customer, employee, insured, days);
        this.loadCapacity = loadCapacity;
        this.engineCapacity = engineCapacity;
    }

    @Override
    protected double calculatePricePerDay() {
        return 0.10 * loadCapacity;
    }

    @Override
    protected double calculateInsurancePerDay() {
        return 0.01 * engineCapacity;
    }
}
