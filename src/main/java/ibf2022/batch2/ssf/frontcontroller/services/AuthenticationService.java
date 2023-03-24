package ibf2022.batch2.ssf.frontcontroller.services;

import java.io.StringReader;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class AuthenticationService {

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
		try {
			resp = template.exchange(req, String.class);
		} catch (Exception ex) {
			throw ex;
		}

		String payload = resp.getBody();
		JsonReader reader = Json.createReader(new StringReader(payload));
		JsonObject json = reader.readObject();

		




	}

	// TODO: Task 3
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to disable a user account for 30 mins
	public void disableUser(String username) {
	}

	// TODO: Task 5
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to check if a given user's login has been disabled
	public boolean isLocked(String username) {
		return false;
	}


	public static JsonObject toJSON(String json){
		JsonReader r = Json.createReader(new StringReader(json));
		return r.readObject();
	}
}
