package br.com.san.desafiojavajr.web.dto.request;

import br.com.san.desafiojavajr.domain.model.Livro;

public record UpdateLivroDTO(String titulo, String autor, Integer anoPublicacao) {

	public void applyTo(Livro livro) {
		livro.setTitulo(titulo);
		livro.setAutor(autor);
		livro.setAnoPublicacao(anoPublicacao);
	}

}
