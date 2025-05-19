package com.ifpb.ads.fliplearn.service;

import com.ifpb.ads.fliplearn.entity.Categoria;
import com.ifpb.ads.fliplearn.repository.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoriaServiceTest {

    private CategoriaRepository repository;
    private CategoriaService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(CategoriaRepository.class);
        service = new CategoriaService();
    }

    @Test
    void testFindCategoriaById_CategoriaExiste() {
        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNome("Ciências");

        when(repository.findById(1L)).thenReturn(Optional.of(categoria));

        Categoria encontrado = service.getCategoriaById(1L).orElse(null);

        assertNotNull(encontrado);
        assertEquals("Ciências", encontrado.getNome());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testFindCategoriaById_CategoriaNaoExiste() {
        when(repository.findById(2L)).thenReturn(Optional.empty());

        Exception excecao = assertThrows(RuntimeException.class, () -> {
            service.getCategoriaById(2L);
        });

        assertEquals("Categoria não encontrada com id: 2", excecao.getMessage());
        verify(repository, times(1)).findById(2L);
    }

    @Test
    void testSaveCategoria() {
        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNome("Matemática");

        when(repository.save(categoria)).thenReturn(categoria);

        Categoria salvo = service.createCategoria(categoria);

        assertNotNull(salvo);
        assertEquals("Matemática", salvo.getNome());
        verify(repository, times(1)).save(categoria);
    }

    @Test
    void testGetCategoriasPrincipais() {
        Categoria categoria1 = new Categoria();
        categoria1.setId(1L);
        categoria1.setNome("Ciências");

        Categoria categoria2 = new Categoria();
        categoria2.setId(2L);
        categoria2.setNome("Linguagens");

        List<Categoria> categoriasMock = List.of(categoria1, categoria2);

        when(repository.findByCategoriaPaiIsNull()).thenReturn(categoriasMock);

        List<Categoria> resultado = service.getCategoriasPrincipais();

        assertEquals(2, resultado.size());
        assertEquals("Ciências", resultado.get(0).getNome());
        assertEquals("Linguagens", resultado.get(1).getNome());
        verify(repository, times(1)).findByCategoriaPaiIsNull();
    }

    @Test
    void testGetSubcategorias() {
        Categoria categoriaPai = new Categoria();
        categoriaPai.setId(1L);
        categoriaPai.setNome("Ciências");

        Categoria subcategoria = new Categoria();
        subcategoria.setId(2L);
        subcategoria.setNome("Biologia");
        subcategoria.setCategoriaPai(categoriaPai);

        List<Categoria> subcategoriasMock = List.of(subcategoria);

        when(repository.findByCategoriaPaiId(1L)).thenReturn(subcategoriasMock);

        List<Categoria> resultado = service.getSubcategorias(1L);

        assertEquals(1, resultado.size());
        assertEquals("Biologia", resultado.get(0).getNome());
        verify(repository, times(1)).findByCategoriaPaiId(1L);
    }

    @Test
    void testUpdateCategoria() {
        Categoria categoriaOriginal = new Categoria();
        categoriaOriginal.setId(1L);
        categoriaOriginal.setNome("Geografia");

        Categoria categoriaAtualizada = new Categoria();
        categoriaAtualizada.setId(1L);
        categoriaAtualizada.setNome("História");

        when(repository.save(categoriaAtualizada)).thenReturn(categoriaAtualizada);

        Categoria resultado = service.updateCategoria(1L, categoriaAtualizada);

        assertNotNull(resultado);
        assertEquals("História", resultado.getNome());
        verify(repository, times(1)).save(categoriaAtualizada);
    }

    @Test
    void testDeleteCategoria() {
        Long id = 1L;

        service.deleteCategoria(id);

        verify(repository, times(1)).deleteById(id);
    }
}
