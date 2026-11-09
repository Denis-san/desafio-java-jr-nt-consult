package br.com.san.desafiojavajr.web.dto.request;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import br.com.san.desafiojavajr.domain.model.Livro;

class CreateLivroDTOTest {

	@Test
	void given_livroDTOData_when_toEntity_then_returnEntityObjectFiled() {
		CreateLivroDTO dto = new CreateLivroDTO("Titulo1", "Autor1", 1998);

		Livro expected = new Livro("Titulo1", "Autor1", 1998);

		Livro entityData = dto.toEntity();

		assertThat(entityData).usingRecursiveAssertion().isEqualTo(expected);
	}

}
