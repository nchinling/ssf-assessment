package ibf2022.batch2.ssf.frontcontroller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ibf2022.batch2.ssf.frontcontroller.services.AuthenticationService;

@Controller
@RequestMapping
public class ProtectedController {

	@Autowired
	private AuthenticationService authSvc;

	// TODO Task 5
	// Write a controller to protect resources rooted under /protected

	@GetMapping(path="/protected")
	public String getIndex(Model model) {
		

		return "view1";
	}

}
