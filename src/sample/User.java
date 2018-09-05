package sample;

public class User {
    
//    user login
    private  String Email;
    private  String password;
    
//    user registration
    private String firstName;
    private  String lastName;

// for user login
    public User(String Email, String password) {
        this.Email = Email;
        this.password = password;
    }
// for user sign up
    public User(String Email, String password, String firstName, String lastName) {
        this.Email = Email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
