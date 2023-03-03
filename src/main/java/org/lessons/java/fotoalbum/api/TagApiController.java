package org.lessons.java.fotoalbum.api;

import java.util.List;
import java.util.Optional;

import org.lessons.java.fotoalbum.model.Tag;
import org.lessons.java.fotoalbum.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/tags")
public class TagApiController {

	@Autowired
	private TagRepository tagRepo;

	@GetMapping
	public List<Tag> index(@RequestParam(name = "tag", required = false) String keyword) {
		if (keyword == null || keyword.isEmpty())
			return tagRepo.findAll();
		else
			return tagRepo.findByNameLike("%" + keyword + "%");
	}

	@GetMapping("{id}")
	public ResponseEntity<Tag> show(@PathVariable(name = "id") long id) {
		Optional<Tag> result = tagRepo.findById(id);

		if (result.isPresent())
			return new ResponseEntity<Tag>(result.get(), HttpStatus.OK);
		else
			return new ResponseEntity<Tag>(HttpStatus.NOT_FOUND);
	}

	@PostMapping()
	public Tag create(@RequestBody Tag tag) {
		return tagRepo.save(tag);
	}

	@PutMapping("{id}")
	public Tag edit(@RequestBody Tag tag, @PathVariable(name = "id") long id) {
		Tag editedTag = tagRepo.getReferenceById(id);

		editedTag.setName(tag.getName());

		return tagRepo.save(editedTag);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable(name = "id") long id) {
		tagRepo.deleteById(id);
	}

}
