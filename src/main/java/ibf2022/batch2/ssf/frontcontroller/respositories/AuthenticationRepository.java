package ibf2022.batch2.ssf.frontcontroller.respositories;

import java.io.IOException;
import java.time.Duration;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.batch2.ssf.frontcontroller.models.User;

@Repository
public class AuthenticationRepository {

	// TODO Task 5
	// Use this class to implement CRUD operations on Redis

	 //autowired in a bean.
	 @Autowired @Qualifier("user")

	 private RedisTemplate<String, String> template;
 
 
	 public void save(User user){
		
		//implement timeout of 30 mins
		int timeout = 3;
    	Duration duration = Duration.ofMinutes(timeout);
		this.template.opsForValue().set(user.getUsername(), user.toJSON().toString(), duration);
	 }

	 public void saveUsername(String username){
		
		System.out.printf(">>> USERNAME: %s\n", username);
		//implement timeout of 30 mins
		int timeout = 1;
    	Duration duration = Duration.ofMinutes(timeout);
		this.template.opsForValue().set(username, username, duration);
	 }
 
	
	 //get json string from redis
	 public Optional<String> getUser(String username) throws IOException{
		 String user = template.opsForValue().get(username);
		 if(null == user|| user.trim().length() <= 0){
			 return Optional.empty();
		 }
 
		//  return Optional.of(User.createUserObjectFromRedis(user));
		 return Optional.of(template.opsForValue().get(user));
	 }

}
