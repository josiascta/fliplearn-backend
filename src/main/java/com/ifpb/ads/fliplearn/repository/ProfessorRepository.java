package com.ifpb.ads.fliplearn.repository;

import com.ifpb.ads.fliplearn.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
