package br.com.san.desafiojavajr.web.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.MediaType;
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

import br.com.san.desafiojavajr.exceptions.handler.ErrorReponse;
import br.com.san.desafiojavajr.service.LivroService;
import br.com.san.desafiojavajr.web.dto.request.CreateLivroDTO;
import br.com.san.desafiojavajr.web.dto.request.UpdateLivroDTO;
import br.com.san.desafiojavajr.web.dto.response.LivroDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/livros")
@ApiResponses({
	@ApiResponse(
			responseCode = "500", 
			description = "Erro interno desconhecido",
			content = { @Content(schema = @Schema(implementation = ErrorReponse.class)) }
		)
})
public class LivroController {
	
	private final LivroService service;
	
	public LivroController(LivroService service) {
		this.service = service;
	}

	
	
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Cadastrar Livro", description = "Cria um registro de um livro na base de dados")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Registro criado com sucesso"),
		@ApiResponse(responseCode = "400", description = "Requisição mal formada ou possui campos inválidos e/ou ausentes")}
	)
	public ResponseEntity<LivroDTO> createLivro(@RequestBody @Valid CreateLivroDTO createDTO) {
		
		LivroDTO livro = service.registerLivro(createDTO);
		
		URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(livro.id())
                .toUri();
		
		return ResponseEntity.created(uri).body(livro);
	}
	
	
	
	
	

	@GetMapping
	@Operation(summary = "Listar todos os Livros", description = "Lista todos os Livros existentens na base de dados")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "A lista de Livros disponíveis na base de dados"),
		@ApiResponse(responseCode = "400", description = "Requisição mal formada")	
	})
	public ResponseEntity<List<LivroDTO>> getAllLivros() {
		
		List<LivroDTO> listDTO = service.getAllLivros();
		
		return ResponseEntity.ok(listDTO);
	}
	
	
	
	
	

	@GetMapping("/{id}")
	@Operation(summary = "Obter um Livro", description = "Retorna os dados de um Livro baseado no seu `id`")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Os dados do Livro"),
		@ApiResponse(responseCode = "400", description = "Requisição mal formada")
	})
	public ResponseEntity<LivroDTO> getLivroById(
			@Parameter(description = "Identificador do Livro") @PathVariable Long id) {
		
		LivroDTO livro = service.getLivroById(id);
		
		return ResponseEntity.ok(livro);
	}

	
	
	
	
	
	@PutMapping("/{id}")
	@Operation(summary = "Atualizar Livro", description = "Atualiza os dados de um Livro")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Os novos dados do Livro atualizado"),
		@ApiResponse(responseCode = "400", description = "Requisição mal formada ou possui campos inválidos e/ou ausentes")
	})
	public ResponseEntity<LivroDTO> updateLivroById(
			@Parameter(description = "Identificador do Livro") @PathVariable Long id, 
			@RequestBody @Valid UpdateLivroDTO updateDTO) {
		
		LivroDTO updatedLivro = service.updateLivro(id, updateDTO);
		
		return ResponseEntity.ok(updatedLivro);
	}
	
	
	
	
	
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar Livro", description = "Deletar Livro da base de dados, baseado no seu `id`")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Livro deletado com sucesso"),
		@ApiResponse(responseCode = "400", description = "Requisição mal formada")
	})
	public ResponseEntity<?> deleteLivroById(@Parameter(description = "Identificador do Livro") @PathVariable Long id) {
		
		service.deleteLivroById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	
	
}
