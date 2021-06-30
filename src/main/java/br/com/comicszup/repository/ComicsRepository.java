package br.com.comicszup.repository;

import br.com.comicszup.entity.Comics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComicsRepository extends JpaRepository<Comics, Long> {
}
