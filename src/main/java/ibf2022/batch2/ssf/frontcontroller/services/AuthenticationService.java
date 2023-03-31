package ibf2022.batch2.ssf.frontcontroller.services;

import java.io.IOException;
import java.io.StringReader;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ibf2022.batch2.ssf.frontcontroller.models.User;
import ibf2022.batch2.ssf.frontcontroller.respositories.AuthenticationRepository;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class AuthenticationService {

	@Autowired
    private AuthenticationRepository authrepo;


	public static final String AUTHSITE = "https://auth.chuklee.com/api/authenticate";

	
	// TODO: Task 2
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write the authentication method in here
	public void authenticate(String username, String password) throws Exception {
		
		System.out.printf(">>> svcUSERNAME: %s\n", username);
		System.out.printf(">>> svcPASSWORD: %s\n", password);

		JsonObject userObject = Json.createObjectBuilder()
									.add("username", username)
									.add("password", password)
									.build();
		//HTTP post request. Information sent via http body.
		RequestEntity<String> req = RequestEntity.post(AUTHSITE)
		.accept(MediaType.APPLICATION_JSON)
		.contentType(MediaType.APPLICATION_JSON)
		.body(userObject.toString());

		
		ResponseEntity<String> resp;
		RestTemplate template = new RestTemplate();
		// try {
			resp = template.exchange(req, String.class);
		// } catch (Exception ex) {
		// 	throw ex;
		// }

		String payload = resp.getBody();
		User authUser = User.createUserObject(payload);
		// if (authUser.getMessage() != null){
			System.out.println(authUser.getMessage());
			System.out.println("User authenticated");

	
	}

	// TODO: Task 3
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to disable a user account for 30 mins
	public void disableUser(String username) throws IOException {
		
			authrepo.saveUsername(username);

	}

	// TODO: Task 5
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to check if a given user's login has been disabled
	public boolean isLocked(String username) throws IOException {
		// return false;
		if(authrepo.getUser(username).isPresent()){
			return true;
		}
		return false;
		
	}


	public static JsonObject toJSON(String json){
		JsonReader r = Json.createReader(new StringReader(json));
		return r.readObject();
	}

	public void save(final User user){
        authrepo.save(user);
    }

	public Optional<String> checkUser(final String username) throws IOException{
        return authrepo.getUser(username);
    }  
}
