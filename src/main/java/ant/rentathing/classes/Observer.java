package ant.rentathing.classes;

import java.io.IOException;

public interface Observer<T> {
    void update(T data) throws IOException;
}