package ibf2022.batch2.ssf.frontcontroller.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ibf2022.batch2.ssf.frontcontroller.models.Captcha;
import ibf2022.batch2.ssf.frontcontroller.models.User;
import ibf2022.batch2.ssf.frontcontroller.services.AuthenticationService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping
public class FrontController {

	public boolean authenticated = false;
	public int counter = 0;

	@Autowired
    private AuthenticationService authSvc;

	public static final String ATTR_USER = "user";
	public static final String ATTR_CAPTCHA = "captcha";
	// TODO: Task 2, Task 3, Task 4, Task 6

	@GetMapping(path={"/", "/index.html"})
	public String getIndex(Model model, HttpSession session) throws IOException {

		Captcha captcha = getCaptcha(session);
		User user = getUser(session);

		// model.addAttribute(ATTR_CAPTCHA, captcha);
		// model.addAttribute(ATTR_USER, user);
		System.out.printf("Initial Counter value: %d\n", captcha.getCounter());
		if(captcha.getCounter() > 3){
			captcha.setCounter(0);
			model.addAttribute(ATTR_USER, user);
			authSvc.disableUser(user.getUsername());
			return "view2";
		}

		model.addAttribute(ATTR_CAPTCHA, captcha);
		model.addAttribute(ATTR_USER, user);
		
		return "view0";
	}

	@PostMapping(path="/login")
    public String submitLogin(@ModelAttribute @Valid User user, BindingResult bindings, 
    Model m, HttpSession session) throws Exception{

		// user = getUser(session);
		Captcha captcha = getCaptcha(session);
        if(bindings.hasErrors()){
            return "view0";
		}
		

		System.out.printf(">>> GETCOUNT(after submit): %d\n", captcha.getCounter());

		String username = user.getUsername();
		String password = user.getPassword();

		System.out.printf(">>> USERNAME: %s\n", username);
		System.out.printf(">>> PASSWORD: %s\n", password);

		//write code to perform authentication on getCaptcha answer. If answer is correct
		// i.e. flag is true, perform authentication
		try {
			authSvc.authenticate(username, password);
			authenticated = true;
			System.out.printf(">>> AUTH_STATUS: %s\n", authenticated);
		  }
		  catch(Exception e) {
			authenticated = false;
			System.out.printf(">>> AUTH_STATUS: %s\n", authenticated);
			
		  }

		  if(authenticated == true){
			return "view1";
		  }
		  else{		
			//to track number of login attempt. 
			user = getUser(session);
			user.setUsername(username);
			captcha.increment();
			
			return "redirect:/";}

    }

	private User getUser(HttpSession sess) {
		User user = (User)sess.getAttribute(ATTR_USER);
		if (null == user) {
			user = new User();
			sess.setAttribute(ATTR_USER, user);
		}
		return user;
	}

	private Captcha getCaptcha(HttpSession sess) {
		Captcha captcha = (Captcha)sess.getAttribute(ATTR_CAPTCHA);
		if (null == captcha) {
			captcha = new Captcha();
			sess.setAttribute(ATTR_CAPTCHA, captcha);
		}
		return captcha;
	}
	
}
