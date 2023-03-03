package org.lessons.java.fotoalbum.api;

import java.util.List;
import java.util.Optional;

import org.lessons.java.fotoalbum.model.Category;
import org.lessons.java.fotoalbum.repository.CategoryRepository;
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
@RequestMapping("/api/categories")
public class CategoryApiController {

	@Autowired
	private CategoryRepository categoryRepo;

	@GetMapping
	public List<Category> index(@RequestParam(name = "category", required = false) String keyword) {
		if (keyword == null || keyword.isEmpty())
			return categoryRepo.findAll();
		else
			return categoryRepo.findByNameLike("%" + keyword + "%");
	}

	@GetMapping("{id}")
	public ResponseEntity<Category> show(@PathVariable(name = "id") long id) {
		Optional<Category> result = categoryRepo.findById(id);

		if (result.isPresent())
			return new ResponseEntity<Category>(result.get(), HttpStatus.OK);
		else
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
	}

	@PostMapping()
	public Category create(@RequestBody Category category) {
		return categoryRepo.save(category);
	}

	@PutMapping("{id}")
	public Category edit(@RequestBody Category category, @PathVariable(name = "id") long id) {
		Category editedCategory = categoryRepo.getReferenceById(id);

		editedCategory.setName(category.getName());

		return categoryRepo.save(editedCategory);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable(name = "id") long id) {
		categoryRepo.deleteById(id);
	}

}
