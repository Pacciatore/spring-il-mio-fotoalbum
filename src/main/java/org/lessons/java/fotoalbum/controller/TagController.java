package org.lessons.java.fotoalbum.controller;

import java.util.List;

import org.lessons.java.fotoalbum.model.Tag;
import org.lessons.java.fotoalbum.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tags")
public class TagController {

	@Autowired
	private TagRepository tagRepo;

	@GetMapping
	public String index(Model model) {
		List<Tag> tags = tagRepo.findAllByOrderByName();
		model.addAttribute("tags", tags);

		return "tags/index";
	}

}
