package ibf2022.batch2.ssf.frontcontroller.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class User {
    
    @Size(min=2, message="Please provide a minimum of 2 characters")
    private String username;

    // @NotEmpty(message="Please provide a minimum of 2 characters")
    @Size(min=2, message="Please provide a minimum of 2 characters")
    private String password;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    

}
