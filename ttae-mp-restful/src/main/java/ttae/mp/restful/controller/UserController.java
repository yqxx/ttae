package ttae.mp.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ttae.weixin.security.service.AuthService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	AuthService principalService;

	@GetMapping
	@PreAuthorize("hasRole('RESTFUL')")
	public String user() {
		return "user success";
	}
	
	@GetMapping("/dep/1")
	@PreAuthorize("hasPermission('P001', 1)")
	public String dep1() {
		return "dep1 success";
	}
	
	@GetMapping("/dep/2")
	@PreAuthorize("hasPermission('P001', 2)")
	public String dep2() {
		return "dep2 success";
	}
	
	@GetMapping("/dep/3")
	@PreAuthorize("hasPermission('P001', 4)")
	public String dep3() {
		return "dep3 success";
	}
	
	@GetMapping("/dep/4")
	@PreAuthorize("hasPermission('P001', 8)")
	public String dep4() {
		return "dep4 success";
	}
}
