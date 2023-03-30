package ibf2022.batch2.ssf.frontcontroller.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.validation.constraints.Size;

public class User {
    
    @Size(min=2, message="Please provide a minimum of 2 characters")
    private String username = "";

    // @NotEmpty(message="Please provide a minimum of 2 characters")
    @Size(min=2, message="Please provide a minimum of 2 characters")
    private String password;

    //to receive message from endpoint. 
    private String message;

    private Captcha captcha;

    public User(){
        this.captcha = new Captcha();
    }

    // private int counter;

    // public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String message) {
        this.message = message;
    }

    public User(String username,
        String password, String message) {
        this.username = username;
        this.password = password;
        this.message = message;
    }

    public User(String username,String password, String message,Captcha captcha) {
        this.username = username;
        this.password = password;
        this.message = message;
        this.captcha = captcha;
    }

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public String getMessage() {return message;}
    public void setMessage(String message) {this.message = message;}

    public Captcha getCaptcha() {return captcha;}
    public void setCaptcha(Captcha captcha) {this.captcha = captcha;}


    public static User createUserObject(String json) throws IOException {
        User u = new User();
        try(InputStream is = new ByteArrayInputStream(json.getBytes())){
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            u.setMessage(o.getString("message"));            
        }
        return u;
    }

    public JsonObject toJSON(){
        return Json.createObjectBuilder()
                .add("username", this.getUsername())
                .add("password", this.getPassword())
                .add("message", this.getMessage())
                .build();
    }

}


