package br.com.san.desafiojavajr.web.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.san.desafiojavajr.exceptions.ResourceNotFoundException;
import br.com.san.desafiojavajr.service.LivroService;
import br.com.san.desafiojavajr.web.dto.request.CreateLivroDTO;
import br.com.san.desafiojavajr.web.dto.request.UpdateLivroDTO;
import br.com.san.desafiojavajr.web.dto.response.LivroDTO;

@WebMvcTest
class LivroControllerTest {
	
    private final MockMvc mockMvc;
    
    private final ObjectMapper mapper = new ObjectMapper();
    
    @MockitoBean
    private LivroService service;

    public LivroControllerTest(@Autowired MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}
    
	@Test
    void given_livroObject_when_createlivro_then_returnSavedLivro() throws JsonProcessingException, Exception {
        
		LivroDTO livroDTO = new LivroDTO(1L, "Titulo1", "Autor1", 1998);
		
        given(service.registerLivro(any(CreateLivroDTO.class)))
            .willReturn(new LivroDTO(1L, "Titulo1", "Autor1", 1998));
        
        ResultActions response = mockMvc.perform(post("/livros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(livroDTO)));
        
        response.andDo(print()).
            andExpect(status().isCreated())
            .andExpect(jsonPath("$.titulo", is(livroDTO.titulo())))
            .andExpect(jsonPath("$.autor", is(livroDTO.autor())))
            .andExpect(jsonPath("$.anoPublicacao", is(livroDTO.anoPublicacao())));
    }
	
	
	
	@Test
    void given_invalidDTOFields_when_createlivro_then_returnBadRequest() throws JsonProcessingException, Exception {
        
		CreateLivroDTO livroDTO = new CreateLivroDTO(" ", null, null);
		
        ResultActions response = mockMvc.perform(post("/livros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(livroDTO)));
        
        response.andDo(print()).andExpect(status().isBadRequest());
    }
	
	
	
	
	@Test
    void given_noParams_when_getAllLivros_then_returnAllLivros() throws JsonProcessingException, Exception {
        
		LivroDTO l1 = new LivroDTO(1L, "Titulo1", "Autor1", 1998);
		LivroDTO l2 = new LivroDTO(2L, "Titulo2", "Autor2", 2000);
		LivroDTO l3 = new LivroDTO(3L, "Titulo3", "Autor3", 2026);

		List<LivroDTO> list = List.of(l1, l2, l3);

		given(service.getAllLivros()).willReturn(list);

		ResultActions response = mockMvc.perform(get("/livros"));
        
        response.
            andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$.size()", is(list.size())));
    }
	
	
	
	
	@Test
    void given_noParams_when_getAllLivros_then_returnEmptyList() throws JsonProcessingException, Exception {
        
		given(service.getAllLivros()).willReturn(new ArrayList<>());

		ResultActions response = mockMvc.perform(get("/livros"));
        
        response.
            andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$.size()", is(0)));
    }
	
	
	
	
	@Test
    void given_id_when_getLivroById_then_returnLivroObject() throws JsonProcessingException, Exception {
        
		LivroDTO livroDTO = new LivroDTO(1l, "Titulo1", "Autor1", 1998);
		
        given(service.getLivroById(1L)).willReturn(livroDTO);
        
        ResultActions response = mockMvc.perform(get("/livros/{id}", 1L));
        
        response.
            andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$.titulo", is(livroDTO.titulo())))
            .andExpect(jsonPath("$.autor", is(livroDTO.autor())))
            .andExpect(jsonPath("$.anoPublicacao", is(livroDTO.anoPublicacao())));
    }
	
	
	
	
	@Test
    void given_notExistingId_when_getLivroById_then_throwResourceNotFoundException() throws JsonProcessingException, Exception {
        
        given(service.getLivroById(anyLong())).willThrow(ResourceNotFoundException.class);
        
        ResultActions response = mockMvc.perform(get("/livros/{id}", 1L));
        
        response.andExpect(status().isNotFound()).andDo(print());
    }
	
	
	
	
	@Test
    void given_id_when_deleteLivroById_then_returnNotContent() throws JsonProcessingException, Exception {
        
        ResultActions response = mockMvc.perform(delete("/livros/{id}", 1L));
        
        response.andExpect(status().isNoContent()).andDo(print());
        
        verify(service, times(1)).deleteLivroById(1L);
    }
	
	
	
	@Test
    void given_livroObject_when_updateLivroById_then_returnUpdatedLivro() throws JsonProcessingException, Exception {
        
		LivroDTO livroDTO = new LivroDTO(1L, "Titulo1", "Autor1", 1998);
		
        given(service.updateLivro(anyLong(), any(UpdateLivroDTO.class)))
            .willReturn(new LivroDTO(1L, "Titulo1", "Autor1", 1998));
        
        ResultActions response = mockMvc.perform(put("/livros/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(livroDTO)));
        
        response.andDo(print()).
            andExpect(status().isOk())
            .andExpect(jsonPath("$.titulo", is(livroDTO.titulo())))
            .andExpect(jsonPath("$.autor", is(livroDTO.autor())))
            .andExpect(jsonPath("$.anoPublicacao", is(livroDTO.anoPublicacao())));
    }
	
	
	
	@Test
    void given_livroObject_when_updateLivroById_then_returnBadRequest() throws JsonProcessingException, Exception {
        
		UpdateLivroDTO livroDTO = new UpdateLivroDTO(" ", null, null);
		
        ResultActions response = mockMvc.perform(put("/livros/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(livroDTO)));
        
        response.andDo(print()).andExpect(status().isBadRequest());
    }
	
	
	
	@Test
	void given_notExistingId_when_updateLivroById_then_returnResourceNotFoundException() throws JsonProcessingException, Exception {
		
		UpdateLivroDTO livroDTO = new UpdateLivroDTO("Titulo1", "Autor1", 1998);
		
		given(service.updateLivro(anyLong(), any(UpdateLivroDTO.class)))
			.willThrow(ResourceNotFoundException.class);
	        
		ResultActions response = mockMvc.perform(put("/livros/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(livroDTO)));
        
        response.andExpect(status().isNotFound()).andDo(print());
	}

}
