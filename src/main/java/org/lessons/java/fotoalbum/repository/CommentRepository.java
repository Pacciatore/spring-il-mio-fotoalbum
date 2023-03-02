package org.lessons.java.fotoalbum.repository;

import org.lessons.java.fotoalbum.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
