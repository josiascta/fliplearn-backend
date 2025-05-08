package com.ifpb.ads.fliplearn.service;

import com.ifpb.ads.fliplearn.model.Curso;
import com.ifpb.ads.fliplearn.repository.CursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CursoServiceTest {

    private CursoRepository repository;
    private CursoService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(CursoRepository.class);
        service = new CursoService(repository);
    }


    @Test
    void testFindCursoById_CursoExiste() {
        Curso curso = new Curso();
        curso.setId(1L);
        curso.setNome("Matemática Básica");

        when(repository.findById(1L)).thenReturn(Optional.of(curso));

        Curso encontrado = service.findCursoById(1L);

        assertNotNull(encontrado);
        assertEquals("Matemática Básica", encontrado.getNome());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testFindCursoById_CursoNaoExiste() {
        when(repository.findById(2L)).thenReturn(Optional.empty());

        Exception excecao = assertThrows(RuntimeException.class, () -> {
            service.findCursoById(2L);
        });

        assertEquals("Curso não encontrado com id: 2", excecao.getMessage());
        verify(repository, times(1)).findById(2L);
    }

    @Test
    void testSaveCurso(){
        Curso curso = new Curso();
        curso.setId(1L);
        curso.setNome("Matemática Básica");

        when(repository.save(curso)).thenReturn(curso);

        Curso encontrado = service.save(curso);

        assertNotNull(encontrado);
        assertEquals("Matemática Básica", encontrado.getNome());
        verify(repository, times(1)).save(curso);
    }

    @Test
    void testGetAll() {
        Curso curso1 = new Curso();
        curso1.setId(1L);
        curso1.setNome("Matemática Básica");

        Curso curso2 = new Curso();
        curso2.setId(2L);
        curso2.setNome("Português Intermediário");

        List<Curso> cursosMock = List.of(curso1, curso2);

        // Configura o comportamento do mock
        when(repository.findAll()).thenReturn(cursosMock);

        // Executa o método do service
        List<Curso> resultado = service.getAll();

        // Verificações
        assertEquals(2, resultado.size());
        assertEquals("Matemática Básica", resultado.get(0).getNome());
        assertEquals("Português Intermediário", resultado.get(1).getNome());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testUpdateCurso() {
        Curso cursoOriginal = new Curso();
        cursoOriginal.setId(1L);
        cursoOriginal.setNome("Matemática Básica");

        Curso cursoAtualizado = new Curso();
        cursoAtualizado.setId(1L);
        cursoAtualizado.setNome("Português Intermediário");

        when(repository.save(cursoAtualizado)).thenReturn(cursoAtualizado);

        Curso resultado = service.update(1L, cursoAtualizado);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Português Intermediário", resultado.getNome());

        verify(repository, times(1)).save(cursoAtualizado);
    }

    @Test
    void testUpdateCursoComIdDiferente_DeveLancarExcecao() {
        Curso curso = new Curso();
        curso.setId(2L); // ID diferente do que será passado no update
        curso.setNome("Ciência de Dados");

        Long idPassado = 1L;

        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
            service.update(idPassado, curso);
        });

        assertEquals("ID do curso não corresponde ao ID fornecido.", excecao.getMessage());
        verify(repository, never()).save(any()); // Garante que o save não foi chamado
    }

    @Test
    void testDeleteCurso() {
        Long id = 1L;

        // Não precisamos configurar comportamento, apenas verificar se foi chamado
        service.deleteCourse(id);

        // Verifica se o método deleteById foi chamado com o ID correto
        verify(repository, times(1)).deleteById(id);
    }

}
