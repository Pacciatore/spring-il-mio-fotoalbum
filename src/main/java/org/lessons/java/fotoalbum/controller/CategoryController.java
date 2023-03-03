package org.lessons.java.fotoalbum.controller;

import java.util.List;

import org.lessons.java.fotoalbum.model.Category;
import org.lessons.java.fotoalbum.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepo;

	@GetMapping()
	public String index(Model model) {
		List<Category> categories = categoryRepo.findAllByOrderByName();
		model.addAttribute("categories", categories);

		return "categories/index";
	}

	@GetMapping("{id}")
	public String show(@PathVariable("id") long id, Model model) {
		Category category = categoryRepo.getReferenceById(id);
		model.addAttribute("category", category);

		return "categories/show";
	}

}
