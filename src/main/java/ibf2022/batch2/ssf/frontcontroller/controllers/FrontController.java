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


	@Autowired
    private AuthenticationService authSvc;

	public static final String ATTR_USER = "user";
	public static final String ATTR_CAPTCHA = "captcha";
	// TODO: Task 2, Task 3, Task 4, Task 6

	@GetMapping(path={"/", "/index.html"})
	public String getIndex(Model model, HttpSession session) throws IOException {

		Captcha captcha = getCaptcha(session);
		User user = getUser(session);

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

		
		Captcha captcha = getCaptcha(session);
        if(bindings.hasErrors()){
            m.addAttribute(ATTR_CAPTCHA, captcha);
			return "view0";
		}
		

		int count = user.getCaptcha().getCounter();
		float userAnswer = user.getCaptcha().getUserAnswer();
		System.out.printf(">>> GETCOUNT(after submit): %d\n", count);
		System.out.println("My float value is: \n" + userAnswer);
		System.out.printf("My float value is: %.2f\n", userAnswer);

		if (captcha.checkAnswer(userAnswer)){
			System.out.println("Your answer is correct");
		}
		else{
			System.out.println("your answer is wrong");
		}

		String username = user.getUsername();
		String password = user.getPassword();

		System.out.printf(">>> USERNAME: %s\n", username);
		System.out.printf(">>> PASSWORD: %s\n", password);

		try {
			authSvc.authenticate(username, password);
			authenticated = true;
			System.out.printf(">>> AUTH_STATUS: %s\n", authenticated);
		    }
		catch(Exception e) {
			authenticated = false;
			System.out.printf(">>> AUTH_STATUS: %s\n", authenticated);
			
		  }

		System.out.printf(">>> BOOLEAN_ANSWER: %s\n", captcha.checkAnswer(userAnswer));


		  if(authenticated == true || (captcha.checkAnswer(userAnswer) && !authSvc.isLocked(username)))
		  {
			return "/protected/view1";
		  }
		  else{		
			session.setAttribute(ATTR_USER, user);
			captcha.increment();
			
			return "redirect:/";}

    }


	@GetMapping(path="/logout")
	public String getlogout(Model model) throws IOException {

		model.addAttribute(ATTR_CAPTCHA, new Captcha());
		model.addAttribute(ATTR_USER, new User());
		
		return "view0";
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
