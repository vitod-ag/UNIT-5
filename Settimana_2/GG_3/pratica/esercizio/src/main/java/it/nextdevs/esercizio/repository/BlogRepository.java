package it.nextdevs.esercizio.repository;

import it.nextdevs.esercizio.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
}
