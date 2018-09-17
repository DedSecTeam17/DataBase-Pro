package sample.MarketModel;
// UserBuilder Pattern
public class User {
    
//    user loginAdmin
    private  String Email;
    private  String password;
    
//    user registration
    private String firstName;
    private  String lastName;
    private  boolean role;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //  message come from data base
private  String message;

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public  User(UserBuilder UserBuilder)
    {
        setFirstName(UserBuilder.firstName);
        setEmail(UserBuilder.email);
        setLastName(UserBuilder.lastName);
        setPassword(UserBuilder.password);
        setRole(UserBuilder.role);
        setMessage(UserBuilder.message);
    }






    public  static UserBuilder newUser(){
        return  new UserBuilder();
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }
    public String getEmail() {
        return this.Email;
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




    public  static class  UserBuilder{
        private  String firstName;
        private  String lastName;
        private  String email;
        private  boolean role;
        private  String password;
        private  String message;

        private UserBuilder()
        {
        }
        public    UserBuilder firstName(String firstName)
        {
            this.firstName=firstName;
            return  this;
        }
        public  UserBuilder lastName(String lastName)
        {
            this.lastName=lastName;
            return  this;
        }
        public  UserBuilder email(String email)
        {
            this.email=email;
            return  this;
        }
        public  UserBuilder password(String password)
        {
            this.password=password;
            return  this;
        }
//        TRUE FOR ADMIN FALSE FOR SELLER
        public  UserBuilder role(boolean role)
        {
                this.role=role;
            return  this;
        }
        public  UserBuilder message(String message)
        {
            this.message=message;
            return  this;
        }
        public  User build(){
            return  new User(this);
        }




    }
}
