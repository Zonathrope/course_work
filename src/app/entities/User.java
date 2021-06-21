package app.entities;

public class User {

    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (o.getClass() == this.getClass()) {
            User user = (User) o;
            return user.name.equals(name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 31;
        return  31 * result + ( (name != null) ? name.hashCode() : 0);
    }
}
