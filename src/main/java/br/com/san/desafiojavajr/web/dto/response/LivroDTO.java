package br.com.san.desafiojavajr.web.dto.response;

import br.com.san.desafiojavajr.domain.model.Livro;

public record LivroDTO(Long id, String titulo, String autor, Integer anoPublicacao) {

	public LivroDTO(Livro livro) {
		this(livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getAnoPublicacao());
	}

}
