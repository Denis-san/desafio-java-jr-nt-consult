package br.com.san.desafiojavajr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.san.desafiojavajr.domain.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

}
