package com.ifpb.ads.fliplearn.controller;


import com.ifpb.ads.fliplearn.entity.Role;
import com.ifpb.ads.fliplearn.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cargo")
@RequiredArgsConstructor
public class RoleController {

    private final RoleRepository roleRepository;

    @GetMapping
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
