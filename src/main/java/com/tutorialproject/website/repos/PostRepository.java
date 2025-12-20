package com.tutorialproject.website.repos;

import com.tutorialproject.website.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

}
