package com.ifpb.ads.fliplearn.service;

import com.ifpb.ads.fliplearn.entity.Questao;
import com.ifpb.ads.fliplearn.repository.QuestaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuestaoServiceTest {

    @InjectMocks
    private QuestaoService questaoService;

    @Mock
    private QuestaoRepository questaoRepository;

    private Questao questao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        questao = new Questao();
        questao.setId(1L);
        questao.setEnunciado("Qual é a capital do Brasil?");
        questao.setAlternativas(Arrays.asList("São Paulo", "Brasília", "Rio de Janeiro"));
        questao.setRespostaCorreta("Brasília");
        questao.setTipo("multipla_escolha");
    }

    @Test
    void testSaveQuestao() {
        when(questaoRepository.save(any(Questao.class))).thenReturn(questao);

        Questao saved = questaoService.saveQuestao(questao);

        assertNotNull(saved);
        assertEquals("Brasília", saved.getRespostaCorreta());
        verify(questaoRepository, times(1)).save(questao);
    }

    @Test
    void testFindAll() {
        when(questaoRepository.findAll()).thenReturn(Arrays.asList(questao));

        List<Questao> result = questaoService.findAll();

        assertEquals(1, result.size());
        assertEquals("Qual é a capital do Brasil?", result.get(0).getEnunciado());
        verify(questaoRepository, times(1)).findAll();
    }

    @Test
    void testGetById() {
        when(questaoRepository.findById(1L)).thenReturn(Optional.of(questao));

        Questao found = questaoService.getById(1L);

        assertNotNull(found);
        assertEquals(1L, found.getId());
        verify(questaoRepository, times(1)).findById(1L);
    }

    @Test
    void testGetById_NotFound() {
        when(questaoRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> questaoService.getById(2L));
        assertEquals("Questão não encontrado com id: 2", ex.getMessage());
    }

    @Test
    void testUpdateSuccess() {
        when(questaoRepository.save(questao)).thenReturn(questao);

        Questao updated = questaoService.update(1L, questao);

        assertEquals(1L, updated.getId());
        verify(questaoRepository, times(1)).save(questao);
    }

    @Test
    void testUpdateMismatchId() {
        questao.setId(1L);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
            questaoService.update(2L, questao)
        );

        assertEquals("ID da Questão não corresponde ao ID fornecido.", ex.getMessage());
    }

    @Test
    void testDelete() {
        doNothing().when(questaoRepository).deleteById(1L);

        questaoService.delete(1L);

        verify(questaoRepository, times(1)).deleteById(1L);
    }
}
