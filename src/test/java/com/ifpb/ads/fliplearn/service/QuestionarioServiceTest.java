package com.ifpb.ads.fliplearn.service;

import com.ifpb.ads.fliplearn.entity.Questionario;
import com.ifpb.ads.fliplearn.repository.QuestionarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuestionarioServiceTest {

    private QuestionarioRepository repository;
    private QuestionarioService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(QuestionarioRepository.class);
        service = new QuestionarioService(repository);
    }

    @Test
    void testFindQuestionarioById_QuestionarioExiste() {
        Questionario questionario = new Questionario();
        questionario.setId(1L);
        

        when(repository.findById(1L)).thenReturn(Optional.of(questionario));

        Questionario encontrado = service.getQuestionarioById(1L).orElse(null);

        assertNotNull(encontrado);
       
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testFindQuestionarioById_QuestionarioNaoExiste() {
        when(repository.findById(2L)).thenReturn(Optional.empty());

        Exception excecao = assertThrows(RuntimeException.class, () -> {
            service.getQuestionarioById(2L);
        });

        assertEquals("Questionário não encontrado com id: 2", excecao.getMessage());
        verify(repository, times(1)).findById(2L);
    }

    @Test
    void testSaveQuestionario() {
        Questionario questionario = new Questionario();
        questionario.setId(1L);
        

        when(repository.save(questionario)).thenReturn(questionario);

        Questionario salvo = service.createQuestionario(questionario);

        assertNotNull(salvo);
       
        verify(repository, times(1)).save(questionario);
    }

    @Test
    void testGetQuestionariosByCategoria() {
        Questionario questionario1 = new Questionario();
        questionario1.setId(1L);
      

        Questionario questionario2 = new Questionario();
        questionario2.setId(2L);
        

        List<Questionario> questionariosMock = List.of(questionario1, questionario2);

        when(repository.findByCategoriaId(1L)).thenReturn(questionariosMock);

        List<Questionario> resultado = service.getQuestionariosByCategoria(1L);

        assertEquals(2, resultado.size());
        
        verify(repository, times(1)).findByCategoriaId(1L);
    }

    @Test
    void testUpdateQuestionario() {
        Questionario questionarioOriginal = new Questionario();
        questionarioOriginal.setId(1L);
       

        Questionario questionarioAtualizado = new Questionario();
        questionarioAtualizado.setId(1L);
        

        when(repository.save(questionarioAtualizado)).thenReturn(questionarioAtualizado);

        Questionario resultado = service.updateQuestionario(1L, questionarioAtualizado);

        assertNotNull(resultado);
       verify(repository, times(1)).save(questionarioAtualizado);
    }

    @Test
    void testDeleteQuestionario() {
        Long id = 1L;

        service.deleteQuestionario(id);

        verify(repository, times(1)).deleteById(id);
    }
}
