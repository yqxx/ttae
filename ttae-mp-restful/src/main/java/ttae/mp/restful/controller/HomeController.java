package ttae.mp.restful.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ttae.weixin.http.ResponseEntity;

public class HomeController {

	@GetMapping
	public ResponseEntity<?> home() {
		return ResponseEntity.ok();
	}
}
