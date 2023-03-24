package ibf2022.batch2.ssf.frontcontroller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ibf2022.batch2.ssf.frontcontroller.models.User;
import ibf2022.batch2.ssf.frontcontroller.services.AuthenticationService;
import jakarta.validation.Valid;

@Controller
@RequestMapping
public class FrontController {

	@Autowired
    private AuthenticationService authSvc;


	public static final String ATTR_USER = "user";
	// TODO: Task 2, Task 3, Task 4, Task 6

	@GetMapping(path={"/", "/index.html"})
	public String getIndex(Model model) {

		//Http session removed for time being. 
		// Order order = getOrder(sess);
		model.addAttribute(ATTR_USER, new User());
		
		return "view0";
	}

	@PostMapping(path="/login")
    public String submitLogin(@ModelAttribute @Valid User user, BindingResult bindings, 
    Model m) throws Exception{

        if(bindings.hasErrors()){
            return "view0";
		}
        

		String username = user.getUsername();
		String password = user.getPassword();

		System.out.printf(">>> USERNAME: %s\n", username);
		System.out.printf(">>> PASSWORD: %s\n", password);

		
		authSvc.authenticate(username, password);


        return "test";

    }
	
}
