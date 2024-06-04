package ant.rentathing.Classes.Products;

import ant.rentathing.Classes.Product;
import ant.rentathing.Classes.User;

import java.util.List;

public class Truck extends Product {
    int loadCapacity;
    int engineCapacity;

    public Truck(String description, boolean availability, List<String> customer, User employee, int loadCapacity, int engineCapacity) {
        super(description, 0.10 * loadCapacity, 0.01 * engineCapacity, availability, customer, employee);
        this.loadCapacity = loadCapacity;
        this.engineCapacity = engineCapacity;
    }
}
