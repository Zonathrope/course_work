package app.model;

import java.util.Map;

public interface Model<E, T> {
    void add(E key, T tender);
    void remove(E key);
    T getModelElement(E key);
    Map<E, T> getModel();
    Model instance = Model.getInstance();
    static Model getInstance() {
        return instance;
    }

}
