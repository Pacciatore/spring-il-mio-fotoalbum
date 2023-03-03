package org.lessons.java.fotoalbum.controller;

import java.util.List;

import org.lessons.java.fotoalbum.model.Tag;
import org.lessons.java.fotoalbum.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

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

	@GetMapping("{id}")
	public String show(@PathVariable("id") long id, Model model) {
		Tag tag = tagRepo.getReferenceById(id);
		model.addAttribute("tag", tag);

		return "tags/show";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") long id, Model model) {
		model.addAttribute("tag", tagRepo.getReferenceById(id));

		return "tags/edit";
	}

	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("tag") Tag formTag, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "tags/edit";
		}

		tagRepo.save(formTag);

		return "redirect:/tags/" + formTag.getId();
	}

	@GetMapping("/create")
	public String create(Model model) {
		Tag tag = new Tag();
		model.addAttribute("tag", tag);

		return "tags/create";
	}

	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("tag") Tag formTag, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "tags/create";
		}
		tagRepo.save(formTag);

		return "redirect:/tags";
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") long id) {
		tagRepo.deleteById(id);

		return "redirect:/tags";
	}
}
