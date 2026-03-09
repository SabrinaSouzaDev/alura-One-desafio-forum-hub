package com.forumhub.api.forumhub.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.forumhub.api.forumhub.domain.topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TopicoRepository extends JpaRepository<Topico, Long> {
Page<Topico> findAllByAtivoTrue(Pageable pageable);
}
