package br.com.san.desafiojavajr.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.san.desafiojavajr.domain.model.Livro;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/livros")
public class LivroController {

	@PostMapping("")
	public ResponseEntity<Livro> createLivro(@RequestBody Livro createDTO) {
		
		// TODO 
		Livro livro = createDTO;
		
		URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(livro.getId())
                .toUri();
		
		return ResponseEntity.created(uri).body(livro);
	}
	
	
	

	@GetMapping("")
	public ResponseEntity<List<Livro>> getAllLivros() {
		// TODO 
		return ResponseEntity.ok(List.of(new Livro("Título livro", "Nome Autor", 2026)));
	}
	
	
	
	

	@GetMapping("/{id}")
	public ResponseEntity<Livro> getLivroById(@PathVariable Long id) {
		// TODO 
		return ResponseEntity.ok(new Livro("Título livro", "Nome Autor", 2026));
	}

	
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Livro> updateLivroById(@PathVariable Long id, @RequestBody Livro updateDTO) {
		//TODO: process PUT request
		return ResponseEntity.ok(updateDTO);
	}
	
	
	
	

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteLivroById(@PathVariable Long id) {
		// TODO 
		return ResponseEntity.noContent().build();
	}
}
