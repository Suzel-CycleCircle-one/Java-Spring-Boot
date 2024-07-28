package com.suzel.production.ready.features.Production.ready.repositories;

import com.suzel.production.ready.features.Production.ready.entities.PostEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEnity, Long> {
}
