package br.com.san.desafiojavajr.web.dto.response;

import br.com.san.desafiojavajr.domain.model.Livro;

public record LivroDTO(Long id, String titulo, String autor, Integer anoPublicacao) {

	public static LivroDTO from(Livro livro) {
		if (livro == null)
			throw new IllegalArgumentException("O livro não deve estar null");

		return new LivroDTO(livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getAnoPublicacao());
	}

}
