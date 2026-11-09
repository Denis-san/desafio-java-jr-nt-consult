package br.com.san.desafiojavajr.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.san.desafiojavajr.domain.model.Livro;

@DataJpaTest
class LivroRepositoryTest {
	
	private final LivroRepository repository;
	
	LivroRepositoryTest(@Autowired LivroRepository repository) {
		this.repository = repository;
	}
	
	
	@Test
	void given_livroObject_when_save_then_incrementId() {
		Livro livro1 = repository.save(new Livro("Titulo1", "Autor1", 2006)); 
		
		assertNotNull(livro1.getId());
		assertTrue(livro1.getId() > 0);
		
		Livro livro2 = repository.save(new Livro("Titulo3", "Autor2", 1998)); 
		
		assertNotNull(livro2.getId());
		assertTrue(livro2.getId() > livro1.getId());

	}
	

	
	
	@Test
	void given_livroObjectWithTituloNull_when_save_then_throwDataIntegrityViolationException() {
		assertThrows(DataIntegrityViolationException.class, () -> {
			repository.saveAndFlush(new Livro(null, "Autor1", 2006)); 
		});
	}
	
	
	
	
	@Test
	void given_livroObjectWithAutorNull_when_save_then_throwDataIntegrityViolationException() {
		assertThrows(DataIntegrityViolationException.class, () -> {
			repository.saveAndFlush(new Livro("Titulo1", null, 2006)); 
		});
	}
	
	
	
	@Test
	void given_livroObjectWithAnoPublicacaoNull_when_save_then_throwDataIntegrityViolationException() {
		assertThrows(DataIntegrityViolationException.class, () -> {
			repository.saveAndFlush(new Livro("Titulo1", "Autor1", null)); 
		});
	}
	
}
