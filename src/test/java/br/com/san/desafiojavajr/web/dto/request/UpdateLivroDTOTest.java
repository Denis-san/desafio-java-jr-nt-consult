package br.com.san.desafiojavajr.web.dto.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.san.desafiojavajr.domain.model.Livro;

class UpdateLivroDTOTest {

	@Test
	void given_updateLivroDTOData_when_toEntity_then_returnEntityObjectFiled() {
		UpdateLivroDTO dto = new UpdateLivroDTO("Titulo1", "Autor1", 1998);

		Livro expected = new Livro("Titulo1", "Autor1", 1998);
		
		Livro entityData = new Livro("deveMudar", "deveMudar", 3000);

		dto.applyTo(expected);

		assertThat(entityData)
			.usingRecursiveAssertion()
				.isEqualTo(expected);
	}
	
	
	
	@Test
	void given_nullLivroDTO_when_toEntity_then_throwIllegalArgumentException() {
		UpdateLivroDTO dto = new UpdateLivroDTO("Titulo1", "Autor1", 1998);

		assertThrows(IllegalArgumentException.class, () -> {
			dto.applyTo(null);
		});
	}
}
