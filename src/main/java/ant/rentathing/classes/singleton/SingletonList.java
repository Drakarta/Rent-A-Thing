package ant.rentathing.classes.singleton;

import ant.rentathing.util.JsonHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@Getter
public abstract class SingletonList<T> {
    protected ArrayList<T> items;

    protected SingletonList(String fileName, TypeReference<ArrayList<T>> typeReference) throws IOException {
        ArrayList<T> itemsFromFile = JsonHandler.readJson(typeReference, fileName);
        items = Objects.requireNonNullElseGet(itemsFromFile, ArrayList::new);
    }

    public void addItem(T item, String fileName) throws IOException {
        items.add(item);
        JsonHandler.writeJson(items, fileName);
    }

    public void removeItem(T item, String fileName) throws IOException {
        items.remove(item);
        JsonHandler.writeJson(items, fileName);
    }

    public void updateItem(T item, String fileName) throws IOException {
        items.set(items.indexOf(item), item);
        JsonHandler.writeJson(items, fileName);
    }
}
