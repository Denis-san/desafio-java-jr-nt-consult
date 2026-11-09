package br.com.san.desafiojavajr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.san.desafiojavajr.domain.model.Livro;
import br.com.san.desafiojavajr.exceptions.ResourceNotFoundException;
import br.com.san.desafiojavajr.repository.LivroRepository;
import br.com.san.desafiojavajr.web.dto.request.CreateLivroDTO;
import br.com.san.desafiojavajr.web.dto.request.UpdateLivroDTO;
import br.com.san.desafiojavajr.web.dto.response.LivroDTO;

@Service
public class LivroService {
	private final LivroRepository repository;
	
	public LivroService(LivroRepository repository) {
		this.repository = repository;
	}

	
	
	
	@Transactional(readOnly = false)
	public LivroDTO registerLivro(CreateLivroDTO createDTO) {
		Livro livro = repository.save(createDTO.toEntity());
		
		return LivroDTO.from(livro);
	}
	
	
	

	@Transactional(readOnly = true)
	public List<LivroDTO> getAllLivros() {
		
		List<Livro> allLivros = repository.findAll(); 
		
		return allLivros.stream().map(LivroDTO::from).toList();
	}
	
	

	@Transactional(readOnly = true)
	public LivroDTO getLivroById(Long id) {
		return repository.findById(id)
				.map(LivroDTO::from)
				.orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado!")); 
	}
	
	
	

	@Transactional(readOnly = false)
	public void deleteLivroById(Long id) {
		repository.deleteById(id);
	}
	
	
	
	
	@Transactional(readOnly = false)
	public LivroDTO updateLivro(Long livroToUpdateId, UpdateLivroDTO updateDTO) {
		
		Livro livroToUpdate = repository.findById(livroToUpdateId)
					.orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado!"));
		
		updateDTO.applyTo(livroToUpdate);
		
		Livro livro = repository.save(livroToUpdate);
		
		return LivroDTO.from(livro);
	}

}
