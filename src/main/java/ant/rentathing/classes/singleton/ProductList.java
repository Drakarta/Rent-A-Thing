package ant.rentathing.classes.singleton;

import ant.rentathing.classes.Observer;
import ant.rentathing.classes.products.Product;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductList extends SingletonList<Product> {
    private static final String PRODUCTS_JSON = "products.json";
    private static final List<Observer<List<Product>>> observerList = new ArrayList<>();
    private static ProductList instance;

    private ProductList() throws IOException {
        super(PRODUCTS_JSON, new TypeReference<>() {});
    }

    public static synchronized ProductList getInstance() throws IOException {
        if (instance == null) {
            instance = new ProductList();
        }
        return instance;
    }

    public void addObserver(Observer<List<Product>> observer) {
        observerList.add(observer);
    }

    public void removeObserver(Observer<List<Product>> observer) {
        observerList.remove(observer);
    }

    public void notifyObservers(List<Product> products) throws IOException {
        for (Observer<List<Product>> observer : observerList) {
            observer.update(products);
        }
    }

    public void addProduct(Product product) throws IOException {
        addItem(product, PRODUCTS_JSON);
        notifyObservers(getItems());
    }

    public void removeProduct(Product product) throws IOException {
        removeItem(product, PRODUCTS_JSON);
        notifyObservers(getItems());
    }

    public void updateProduct(Product product) throws IOException {
        updateItem(product, PRODUCTS_JSON);
        notifyObservers(getItems());
    }
}
