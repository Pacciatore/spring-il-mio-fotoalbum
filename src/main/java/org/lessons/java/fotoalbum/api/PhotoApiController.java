package org.lessons.java.fotoalbum.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.lessons.java.fotoalbum.model.Comment;
import org.lessons.java.fotoalbum.model.Photo;
import org.lessons.java.fotoalbum.model.User;
import org.lessons.java.fotoalbum.repository.CommentRepository;
import org.lessons.java.fotoalbum.repository.PhotoRepository;
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
@RequestMapping("/api/photos")
public class PhotoApiController {

	@Autowired
	private PhotoRepository photoRepo;

	@Autowired
	private CommentRepository commentRepo;

	@GetMapping
	public List<Photo> index(@RequestParam(name = "photo", required = false) String keyword) {
		if (keyword == null || keyword.isEmpty())
			return photoRepo.findAll();
		else
			return photoRepo.findByTitleLike("%" + keyword + "%");
	}

	@GetMapping("{id}")
	public ResponseEntity<Photo> show(@PathVariable(name = "id") long id) {
		Optional<Photo> result = photoRepo.findById(id);

		if (result.isPresent())
			return new ResponseEntity<Photo>(result.get(), HttpStatus.OK);
		else
			return new ResponseEntity<Photo>(HttpStatus.NOT_FOUND);
	}

	@PostMapping()
	public Photo create(@RequestBody Photo photo) {
		return photoRepo.save(photo);
	}

	@PutMapping("{id}")
	public Photo edit(@RequestBody Photo photo, @PathVariable(name = "id") long id) {
		Photo editedPhoto = photoRepo.getReferenceById(id);

		editedPhoto.setTitle(photo.getTitle());
		editedPhoto.setDescription(photo.getDescription());
		editedPhoto.setUrl(photo.getUrl());
		editedPhoto.setVisible(photo.isVisible());

		return photoRepo.save(editedPhoto);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable(name = "id") long id) {
		photoRepo.deleteById(id);
	}

//	Inner controller for Comments
	@GetMapping("{id}/comments")
	public List<Comment> showComments(@PathVariable(name = "id") long id) {
		List<Comment> comments = photoRepo.getReferenceById(id).getComments();

		return comments;
	}

//	Ottengo gli users di tutti i commenti
	@GetMapping("{id}/comments/users")
	public List<User> showCommentsUsers(@PathVariable(name = "id") long id) {
		List<Comment> comments = photoRepo.getReferenceById(id).getComments();
		List<User> users = new ArrayList<>();

		for (Comment comment : comments) {
			User cleanedUser = new User();

			cleanedUser.setId(comment.getUser().getId());
			cleanedUser.setUsername(comment.getUser().getUsername());
			cleanedUser.setRoles(comment.getUser().getRoles());
			cleanedUser.setComments(comment.getUser().getComments());
			cleanedUser.setPassword("private");

			users.add(cleanedUser);
		}

		return users;
	}

	@PostMapping("{id}/comments")
	public ResponseEntity<Comment> createComment(@PathVariable(name = "id") long id, @RequestBody Comment comment) {
		Optional<Photo> result = photoRepo.findById(id);

		if (result.isPresent()) {
			comment.setPhoto(result.get());
			commentRepo.save(comment);
			return new ResponseEntity<Comment>(comment, HttpStatus.OK);
		} else
			return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
	}
}
