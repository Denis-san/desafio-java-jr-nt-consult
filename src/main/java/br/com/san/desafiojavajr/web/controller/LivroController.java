package br.com.san.desafiojavajr.web.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.san.desafiojavajr.service.LivroService;
import br.com.san.desafiojavajr.web.dto.request.CreateLivroDTO;
import br.com.san.desafiojavajr.web.dto.request.UpdateLivroDTO;
import br.com.san.desafiojavajr.web.dto.response.LivroDTO;


@RestController
@RequestMapping("/livros")
public class LivroController {
	
	private final LivroService service;
	
	public LivroController(LivroService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<LivroDTO> createLivro(@RequestBody CreateLivroDTO createDTO) {
		
		LivroDTO livro = service.registerLivro(createDTO);
		
		URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(livro.id())
                .toUri();
		
		return ResponseEntity.created(uri).body(livro);
	}
	
	
	

	@GetMapping
	public ResponseEntity<List<LivroDTO>> getAllLivros() {
		
		List<LivroDTO> listDTO = service.getAllLivros();
		
		return ResponseEntity.ok(listDTO);
	}
	
	
	

	@GetMapping("/{id}")
	public ResponseEntity<LivroDTO> getLivroById(@PathVariable Long id) {
		
		LivroDTO livro = service.getLivroById(id);
		
		return ResponseEntity.ok(livro);
	}

	
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<LivroDTO> updateLivroById(@PathVariable Long id, @RequestBody UpdateLivroDTO updateDTO) {
		
		LivroDTO updatedLivro = service.updateLivro(id, updateDTO);
		
		return ResponseEntity.ok(updatedLivro);
	}
	
	
	
	

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteLivroById(@PathVariable Long id) {
		
		service.deleteLivroById(id);
		
		return ResponseEntity.noContent().build();
	}
}
