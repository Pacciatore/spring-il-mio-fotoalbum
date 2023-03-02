package org.lessons.java.fotoalbum.repository;

import java.util.List;

import org.lessons.java.fotoalbum.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	public List<Category> findAllByOrderByName();

}
