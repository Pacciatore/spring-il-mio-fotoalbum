package org.lessons.java.fotoalbum.controller;

import java.util.List;

import org.lessons.java.fotoalbum.model.Category;
import org.lessons.java.fotoalbum.model.Photo;
import org.lessons.java.fotoalbum.model.Tag;
import org.lessons.java.fotoalbum.repository.CategoryRepository;
import org.lessons.java.fotoalbum.repository.PhotoRepository;
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
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/photos")
public class PhotoController {

	@Autowired
	private PhotoRepository photoRepo;

	@Autowired
	private TagRepository tagRepo;

	@Autowired
	private CategoryRepository categoryRepo;

	@GetMapping
	public String index(@RequestParam(name = "photo", required = false) String keyword, Model model) {
		List<Photo> photos = photoRepo.findAll();

		if (keyword == null || keyword.isEmpty())
			photos = photoRepo.findAll();
		else
			photos = photoRepo.findByTitleLike("%" + keyword + "%");

		model.addAttribute("photos", photos);

		return "photos/index";
	}

	@GetMapping("{id}")
	public String show(@PathVariable(name = "id") long id, Model model) {
		Photo photo = photoRepo.getReferenceById(id);
		model.addAttribute("photo", photo);

		return "photos/show";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") long id, Model model) {
		Photo photo = photoRepo.getReferenceById(id);
		List<Tag> tagsList = tagRepo.findAllByOrderByName();
		List<Category> categoriesList = categoryRepo.findAllByOrderByName();

		model.addAttribute("photo", photo);
		model.addAttribute("tagsList", tagsList);
		model.addAttribute("categoriesList", categoriesList);

		return "photos/edit";
	}

	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("tagsList", tagRepo.findAllByOrderByName());
			model.addAttribute("categoriesList", categoryRepo.findAllByOrderByName());
			return "photos/edit";
		}

		photoRepo.save(formPhoto);

		return "redirect:/photos/" + formPhoto.getId();
	}

	@GetMapping("/create")
	public String create(Model model) {
		Photo photo = new Photo();

		List<Category> categories = categoryRepo.findAllByOrderByName();
		List<Tag> tags = tagRepo.findAllByOrderByName();

		model.addAttribute("photo", photo);
		model.addAttribute("categoriesList", categories);
		model.addAttribute("tagsList", tags);

		return "photos/create";
	}

	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("tagsList", tagRepo.findAllByOrderByName());
			model.addAttribute("categoriesList", categoryRepo.findAllByOrderByName());
			return "photos/create";
		}

		photoRepo.save(formPhoto);

		return "redirect:/photos";
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") long id) {
		photoRepo.deleteById(id);

		return "redirect:/photos";
	}

}
