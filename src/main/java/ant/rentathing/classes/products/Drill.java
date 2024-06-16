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
public class Drill extends Product {
    private String brand;
    private String type;

    public Drill(String description, String brand, String type) throws IOException {
        super("Drill", description);
        this.brand = brand;
        this.type = type;
        ProductList.getInstance().addProduct(this);
    }

    @JsonCreator
    public Drill(@JsonProperty("id") String id,
                 @JsonProperty("description") String description,
                 @JsonProperty("availability") boolean availability,
                 @JsonProperty("customer") String customer,
                 @JsonProperty("employee") User employee,
                 @JsonProperty("insured") boolean insured,
                 @JsonProperty("days") int days,
                 @JsonProperty("brand") String brand,
                 @JsonProperty("type") String type) {
        super(id, "Drill", description, availability, customer, employee, insured, days);
        this.brand = brand;
        this.type = type;
    }

    @Override
    public double calculatePricePerDay() {
        return 5;
    }

    @Override
    public double calculateInsurancePerDay() {
        return 1;
    }
}
