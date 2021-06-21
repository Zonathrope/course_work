package app.model;
import app.entities.User;

import java.util.*;

public class UserModel implements Model<String, User>{

    private HashMap<String, User> userModel;

    private UserModel() {
        userModel = new HashMap<>();
    }

    private static UserModel instance = new UserModel();

    public static UserModel getInstance(){
        return instance;
    }

    public void add(String name, User user) {
        userModel.put(name, user);
    }

    public void remove(String key) { userModel.remove(key);}

    public User getModelElement(String key) {
        return userModel.get(key);
    }

    public HashMap<String, User> getModel() {
        return this.userModel;
    }
}