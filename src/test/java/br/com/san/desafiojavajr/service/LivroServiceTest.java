package br.com.san.desafiojavajr.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.san.desafiojavajr.domain.model.Livro;
import br.com.san.desafiojavajr.exceptions.ResourceNotFoundException;
import br.com.san.desafiojavajr.repository.LivroRepository;
import br.com.san.desafiojavajr.web.dto.request.CreateLivroDTO;
import br.com.san.desafiojavajr.web.dto.request.UpdateLivroDTO;
import br.com.san.desafiojavajr.web.dto.response.LivroDTO;

@ExtendWith(MockitoExtension.class)
public class LivroServiceTest {

	@Mock
	private LivroRepository repository;

	@InjectMocks
	private LivroService service;

	@Test
	void given_livroObject_when_registerLivro_then_returnLivroDTO() {
		CreateLivroDTO dto = new CreateLivroDTO("Titulo1", "Autor1", 1998);

		Livro livro = new Livro("Titulo1", "Autor1", 1998);
		livro.setId(1L);

		given(repository.save(dto.toEntity())).willReturn(livro);

		LivroDTO livroDTO = service.registerLivro(dto);

		assertNotNull(livroDTO);
		assertTrue(livroDTO.id() > 0);

	}

	@Test
	void given_nullOnRequiredFields_when_registerLivro_then_throwDataIntegrityViolationException() {
		CreateLivroDTO dto = new CreateLivroDTO(null, null, null);

		given(repository.save(dto.toEntity())).willThrow(DataIntegrityViolationException.class);

		assertThrows(DataIntegrityViolationException.class, () -> {
			service.registerLivro(dto);
		});
	}

	@Test
	void given_noParameters_when_getAllLivros_then_returnList() {

		Livro l1 = new Livro("Titulo1", "Autor1", 1998);
		l1.setId(1L);
		Livro l2 = new Livro("Titulo2", "Autor2", 2000);
		l2.setId(2L);
		Livro l3 = new Livro("Titulo3", "Autor3", 2026);
		l3.setId(3L);

		List<Livro> list = List.of(l1, l2, l3);

		given(repository.findAll()).willReturn(list);

		List<LivroDTO> result = service.getAllLivros();

		assertThat(result).isNotEmpty();

	}

	@Test
	void given_noParameters_when_getAllLivros_then_emptyList() {

		given(repository.findAll()).willReturn(new ArrayList<>());

		List<LivroDTO> result = service.getAllLivros();

		assertThat(result).isEmpty();

	}

	@Test
	void given_id_when_getLivroById_then_returnLivroObject() {

		Livro livro = new Livro("Titulo1", "Autor1", 1998);
		livro.setId(1L);

		given(repository.findById(1L)).willReturn(Optional.of(livro));

		LivroDTO result = service.getLivroById(1L);

		LivroDTO expected = new LivroDTO(1L, "Titulo1", "Autor1", 1998);

		assertThat(result).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void given_id_when_getLivroById_then_throwResourceNotFoundException() {

		given(repository.findById(1L)).willThrow(ResourceNotFoundException.class);

		assertThrows(ResourceNotFoundException.class, () -> {
			service.getLivroById(1L);
		});
	}

	@Test
	void given_id_when_deleteLivroById_then_deleteLivro() {

		service.deleteLivroById(1L);

		verify(repository, times(1)).deleteById(1L);
	}

	@Test
	void given_updateObject_when_updateLivro_then_returnUpdatedLivro() {
		UpdateLivroDTO dto = new UpdateLivroDTO("Titulo1", "Autor1", 1998);

		Livro livro = new Livro("DeveMudar", "DeveMudar", 1000);
		livro.setId(1L);
		
		given(repository.findById(1L)).willReturn(Optional.of(livro));
		
		dto.applyTo(livro);
		
		Livro livroReturn = new Livro("Titulo1", "Autor1", 1998);
		livroReturn.setId(1L);
		
		given(repository.save(livro)).willReturn(livroReturn);

		LivroDTO livroDTO = service.updateLivro(1L, dto);

		LivroDTO expected = new LivroDTO(1L, "Titulo1", "Autor1", 1998);
		
		assertThat(livroDTO).usingRecursiveComparison().isEqualTo(expected);
	}
	
	
	
	
	@Test
	void given_nullOnRequiredFields_when_updateLivro_then_throwDataIntegrityViolationException() {
		UpdateLivroDTO dto = new UpdateLivroDTO(null, null, null);

		Livro livro = new Livro("DeveMudar", "DeveMudar", 1000);
		livro.setId(1L);
		
		given(repository.findById(1L)).willReturn(Optional.of(livro));
		
		
		given(repository.save(livro)).willThrow(DataIntegrityViolationException.class);
		
		assertThrows(DataIntegrityViolationException.class, () -> {
			service.updateLivro(1L, dto);
		});
	}
	
	
	
	
	@Test
	void given_notExistingId_when_updateLivro_then_throwResourceNotFoundException() {
		UpdateLivroDTO dto = new UpdateLivroDTO("Titulo1", "Autor1", 1998);

		given(repository.findById(100L)).willThrow(ResourceNotFoundException.class);
		
		assertThrows(ResourceNotFoundException.class, () -> {
			service.updateLivro(100L, dto);
		});
		
		verify(repository, never()).save(any(Livro.class));
	}

}
