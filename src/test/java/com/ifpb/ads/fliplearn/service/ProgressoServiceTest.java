package com.ifpb.ads.fliplearn.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import com.ifpb.ads.fliplearn.entity.Curso;
import com.ifpb.ads.fliplearn.entity.Progresso;
import com.ifpb.ads.fliplearn.repository.ProgressoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ProgressoServiceTest {

    @Mock
    private ProgressoRepository repository;

    @InjectMocks
    private ProgressoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave_deveSalvarProgresso() {
        Progresso progresso = new Progresso();
        Curso curso = new Curso();

        curso.setNome("Português");
        progresso.setId(1L);
        progresso.setCurso(curso);

        // Supondo que você modificou o método save para retornar o progresso salvo
        when(repository.save(progresso)).thenReturn(progresso);

        Progresso salvo = service.save(progresso);

        assertNotNull(salvo);
        assertEquals(progresso.getId(), salvo.getId());
        assertEquals(progresso.getCurso(), salvo.getCurso());

        verify(repository, times(1)).save(progresso);
    }

    @Test
    void testFindAll_deveRetornarListaDeProgresso() {
        Progresso p1 = new Progresso();
        Progresso p2 = new Progresso();

        when(repository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Progresso> result = service.findAll();

        assertEquals(2, result.size());
        assertTrue(result.contains(p1));
        assertTrue(result.contains(p2));
        verify(repository, times(1)).findAll();
    }
}
