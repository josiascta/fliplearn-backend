package com.ifpb.ads.fliplearn.repository;

import com.ifpb.ads.fliplearn.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
