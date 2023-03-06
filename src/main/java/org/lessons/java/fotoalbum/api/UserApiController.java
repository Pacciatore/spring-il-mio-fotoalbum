package org.lessons.java.fotoalbum.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.lessons.java.fotoalbum.model.User;
import org.lessons.java.fotoalbum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserApiController {

	@Autowired
	private UserRepository userRepo;

	@GetMapping
	public List<User> index() {
		List<User> usersList = userRepo.findAll();
		List<User> cleanedUsersList = new ArrayList<>();

		for (User user : usersList) {
			User cleanedUser = new User();

			cleanedUser.setId(user.getId());
			cleanedUser.setUsername(user.getUsername());
			cleanedUser.setComments(user.getComments());
			cleanedUser.setRoles(user.getRoles());
			cleanedUser.setPassword("private");

			cleanedUsersList.add(cleanedUser);

		}

		return cleanedUsersList;
	}

	@GetMapping("{id}")
	public ResponseEntity<User> show(@PathVariable(name = "id") Integer id) {
		Optional<User> result = userRepo.findById(id);

		if (result.isPresent()) {

			User cleanedUser = new User();

			cleanedUser.setId(result.get().getId());
			cleanedUser.setUsername(result.get().getUsername());
			cleanedUser.setRoles(result.get().getRoles());
			cleanedUser.setComments(result.get().getComments());
			cleanedUser.setPassword("private");

			return new ResponseEntity<User>(cleanedUser, HttpStatus.OK);
		} else
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}

}
