package br.com.san.desafiojavajr.web.dto.request;

import br.com.san.desafiojavajr.domain.model.Livro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateLivroDTO(
		
		@NotBlank
		String titulo,
		
		@NotBlank
		String autor,
		
		@NotNull
		@Positive
		Integer anoPublicacao
	) {

		public Livro toEntity() {
			return new Livro(this.titulo, this.autor, this.anoPublicacao);
		}

}