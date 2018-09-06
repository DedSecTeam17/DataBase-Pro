package sample.MarketModel;
// Builder Pattern
public class User {
    
//    user login
    private  String Email;
    private  String password;
    
//    user registration
    private String firstName;
    private  String lastName;


    public  User(Builder builder)
    {
        setFirstName(builder.firstName);
        setEmail(builder.email);
        setLastName(builder.lastName);
        setPassword(builder.password);
    }
    public  static Builder newUser(){
        return  new Builder();
    }
// fo
// r user login
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
    public  static class  Builder{
        private  String firstName;
        private  String lastName;
        private  String email;
        private  String password;

        private Builder()
        {
        }
        public    Builder firstName(String firstName)
        {
            this.firstName=firstName;
            return  this;
        }
        public  Builder lastName(String lastName)
        {
            this.lastName=lastName;
            return  this;
        }
        public  Builder email(String email)
        {
            this.email=email;
            return  this;
        }
        public  Builder password(String password)
        {
            this.password=password;
            return  this;
        }
        public  User build(){
            return  new User(this);
        }




    }
}
