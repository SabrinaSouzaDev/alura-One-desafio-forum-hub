package com.forumhub.api.forumhub.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.forumhub.api.forumhub.domain.autor.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Page<Autor> findAllByAtivoTrue(Pageable pageable);
}
