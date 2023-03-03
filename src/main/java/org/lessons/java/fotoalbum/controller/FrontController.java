package org.lessons.java.fotoalbum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class FrontController {

	@GetMapping()
	public String index() {
		return "front/homepage";
	}

	@GetMapping("/photo")
	public String show(@RequestParam(name = "photo", required = false) String keyword) {
		return "front/show";
	}

}
