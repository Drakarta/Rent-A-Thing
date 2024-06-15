package ant.rentathing.classes.products;

import ant.rentathing.classes.User;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "productType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Drill.class, name = "Drill"),
        @JsonSubTypes.Type(value = PassengerCar.class, name = "PassengerCar"),
        @JsonSubTypes.Type(value = Truck.class, name = "Truck")
})

@Getter
@Setter
public abstract class Product {
    UUID id;
    String productType;
    String description;
    boolean available;
    String customer;
    User employee;
    double pricePerDay;
    double insurancePerDay;
    boolean insured;
    int days;

    protected Product(String productType, String description){
        this.id = UUID.randomUUID();
        this.productType = productType;
        this.description = description;
        this.available = true;
        this.customer = null;
        this.employee = null;
        this.pricePerDay = calculatePricePerDay();
        this.insurancePerDay = calculateInsurancePerDay();
        this.insured = false;
        this.days = 0;
    }

    @JsonCreator
    protected Product(@JsonProperty("id") String id,
                      @JsonProperty("productType") String productType,
                      @JsonProperty("description") String description,
                      @JsonProperty("availability") boolean availability,
                      @JsonProperty("customer") String customer,
                      @JsonProperty("employee") User employee,
                      @JsonProperty("insured") boolean insured,
                      @JsonProperty("days") int days) {
        this.id = UUID.fromString(id);
        this.productType = productType;
        this.description = description;
        this.available = availability;
        this.customer = customer;
        this.employee = employee;
        this.pricePerDay = calculatePricePerDay();
        this.insurancePerDay = calculateInsurancePerDay();
        this.insured = insured;
        this.days = days;
    }

    protected abstract double calculatePricePerDay();
    protected abstract double calculateInsurancePerDay();

    public double calculateTotalPrice(int days, boolean insurance) {
        double totalPrice = calculatePricePerDay() * days;
        if (insurance) {
            totalPrice += calculateInsurancePerDay() * days;
        }
        return totalPrice;
    }
}
