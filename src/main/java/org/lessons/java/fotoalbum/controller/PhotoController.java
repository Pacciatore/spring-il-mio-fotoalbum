package org.lessons.java.fotoalbum.controller;

import java.util.List;

import org.lessons.java.fotoalbum.model.Photo;
import org.lessons.java.fotoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/photos")
public class PhotoController {

	@Autowired
	private PhotoRepository photosRepo;

	@GetMapping
	public String index(Model model) {
		List<Photo> photos = photosRepo.findAll();
		model.addAttribute("photos", photos);

		return "photos/index";
	}

	@GetMapping("{id}")
	public String show(@PathVariable(name = "id") long id, Model model) {
		Photo photo = photosRepo.getReferenceById(id);
		model.addAttribute("photo", photo);

		return "photos/show";
	}

}
