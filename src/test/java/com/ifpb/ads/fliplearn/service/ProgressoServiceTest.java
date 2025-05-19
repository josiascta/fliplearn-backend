package com.ifpb.ads.fliplearn.service;

import com.ifpb.ads.fliplearn.entity.Curso;
import com.ifpb.ads.fliplearn.entity.Progresso;
import com.ifpb.ads.fliplearn.repository.ProgressoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgressoServiceTest {

    @InjectMocks
    private ProgressoService progressoService;

    @Mock
    private ProgressoRepository progressoRepository;

    private Progresso progresso;
    private Curso curso;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        curso = new Curso();
        curso.setId(1L);
        curso.setNome("Java Básico");
        curso.setCargaHoraria(40.0);

        progresso = new Progresso();
        progresso.setId(1L);
        progresso.setCurso(curso);
        progresso.setDataInicio(new Date());
        progresso.setDataUltimaAtividade(new Date());
        progresso.setNotaMedia(9.5);
        progresso.setFeedback("Muito bom");
        progresso.setPercentualConcluido(0.0);
    }

    @Test
    void testSave() {
        when(progressoRepository.save(progresso)).thenReturn(progresso);

        Progresso saved = progressoService.save(progresso);

        assertNotNull(saved);
        assertEquals("Muito bom", saved.getFeedback());
        verify(progressoRepository, times(1)).save(progresso);
    }

    @Test
    void testFindAll() {
        when(progressoRepository.findAll()).thenReturn(Arrays.asList(progresso));

        List<Progresso> result = progressoService.findAll();

        assertEquals(1, result.size());
        verify(progressoRepository, times(1)).findAll();
    }

    @Test
    void testGetByIdFound() {
        when(progressoRepository.findById(1L)).thenReturn(Optional.of(progresso));

        Progresso found = progressoService.getById(1L);

        assertNotNull(found);
        assertEquals(1L, found.getId());
        verify(progressoRepository, times(1)).findById(1L);
    }

    @Test
    void testGetByIdNotFound() {
        when(progressoRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                progressoService.getById(99L));

        assertEquals("Progresso não encontrado com id: 99", exception.getMessage());
    }

    @Test
    void testUpdateSuccess() {
        when(progressoRepository.save(progresso)).thenReturn(progresso);

        Progresso updated = progressoService.update(1L, progresso);

        assertNotNull(updated);
        assertEquals(1L, updated.getId());
        verify(progressoRepository, times(1)).save(progresso);
    }

    @Test
    void testUpdateIdMismatch() {
        progresso.setId(1L);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                progressoService.update(2L, progresso));

        assertEquals("ID do progresso não corresponde ao ID fornecido.", exception.getMessage());
    }

    @Test
    void testDelete() {
        doNothing().when(progressoRepository).deleteById(1L);

        progressoService.delete(1L);

        verify(progressoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testRecalcularProgresso() {
        progresso.setId(1L);
        double cargaHorariaCompletada = 20.0;

        when(progressoRepository.save(progresso)).thenReturn(progresso);

        progressoService.recalcularProgresso(cargaHorariaCompletada, progresso);

        assertEquals(50.0, progresso.getPercentualConcluido());
        verify(progressoRepository, times(1)).save(progresso);
    }
}
