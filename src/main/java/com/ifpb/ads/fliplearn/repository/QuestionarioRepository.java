package com.ifpb.ads.fliplearn.repository;

import com.ifpb.ads.fliplearn.entity.Questionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionarioRepository extends JpaRepository<Questionario, Long> {


    List<Questionario> findByCategoriaId(Long categoriaId);
}
