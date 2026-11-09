package br.com.san.desafiojavajr.web.dto.request;

import br.com.san.desafiojavajr.domain.model.Livro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateLivroDTO(
		
		@NotBlank
		String titulo,
		
		@NotBlank
		String autor,
		
		@NotNull
		@Positive
		Integer anoPublicacao
	) {

	public void applyTo(Livro livro) {
		
		if(livro == null) throw new IllegalArgumentException("O livro não deve estar null");
		
		livro.setTitulo(titulo);
		livro.setAutor(autor);
		livro.setAnoPublicacao(anoPublicacao);
	}

}
