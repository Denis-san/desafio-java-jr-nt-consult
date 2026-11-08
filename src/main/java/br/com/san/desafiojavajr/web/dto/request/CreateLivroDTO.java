package br.com.san.desafiojavajr.web.dto.request;

import br.com.san.desafiojavajr.domain.model.Livro;

public record CreateLivroDTO(String titulo, String autor, Integer anoPublicacao) {

	public CreateLivroDTO(Livro livro) {
		this(livro.getTitulo(), livro.getAutor(), livro.getAnoPublicacao());
	}

	public Livro toEntity() {
		return new Livro(this.titulo, this.autor, this.anoPublicacao);
	}

}