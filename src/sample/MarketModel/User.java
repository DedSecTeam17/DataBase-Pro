package sample.MarketModel;
// UserBuilder Pattern
public class User {
    
//    user login
    private  String Email;
    private  String password;
    
//    user registration
    private String firstName;
    private  String lastName;


    public  User(UserBuilder UserBuilder)
    {
        setFirstName(UserBuilder.firstName);
        setEmail(UserBuilder.email);
        setLastName(UserBuilder.lastName);
        setPassword(UserBuilder.password);
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




        private  String password;

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
        public  User build(){
            return  new User(this);
        }




    }
}
