package br.com.san.desafiojavajr.web.dto.response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.san.desafiojavajr.domain.model.Livro;

class LivroDTOTest {

	@Test
	void given_livroObject_when_from_then_fillDTOObject() {
		Livro livro = new Livro("Titulo1", "Autor1", 1998);
		livro.setId(1L);

		LivroDTO dto = LivroDTO.from(livro);

		LivroDTO expected = new LivroDTO(1L, "Titulo1", "Autor1", 1998);

		assertThat(dto).usingRecursiveComparison().isEqualTo(expected);

	}
	
	

	@Test
	void given_nullLivro_when_from_then_throwIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> {
			LivroDTO.from(null);
		});
	}
}
