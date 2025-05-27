package com.ifpb.ads.fliplearn.repository;


import com.ifpb.ads.fliplearn.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByNome(String nome);
}
