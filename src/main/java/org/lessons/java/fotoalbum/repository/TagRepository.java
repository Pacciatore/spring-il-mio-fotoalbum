package org.lessons.java.fotoalbum.repository;

import java.util.List;

import org.lessons.java.fotoalbum.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

	public List<Tag> findAllByOrderByName();

}
