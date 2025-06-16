package com.ifpb.ads.fliplearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifpb.ads.fliplearn.entity.VideoAula;

public interface VideoAulaRepository extends JpaRepository<VideoAula, Long> {

    // Métodos adicionais de consulta podem ser definidos aqui, se necessário.
    // Por exemplo, você pode adicionar métodos para buscar vídeos por título, professorId, etc.

}
