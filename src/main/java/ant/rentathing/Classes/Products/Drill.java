package ant.rentathing.Classes.Products;

import ant.rentathing.Classes.Product;
import ant.rentathing.Classes.User;

import java.util.List;

public class Drill extends Product {
    String brand;
    String type;

    public Drill(String description, boolean availability, List<String> customer, User employee, String brand, String type) {
        super(description, 5, 1, availability, customer, employee);
        this.brand = brand;
        this.type = type;
    }
}
