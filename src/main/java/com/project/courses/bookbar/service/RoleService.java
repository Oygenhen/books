package com.project.courses.bookbar.service;

import com.project.courses.bookbar.entity.Role;
import com.project.courses.bookbar.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepo roleRepo;

    public List<Role> findAll() {
        return roleRepo.findAll();
    }

    public Optional<Role> findById(Long id) {
        return roleRepo.findById(id);
    }

    public Role create(Role role) {
        return roleRepo.save(role);
    }

    public Role update(Role role) {
        return roleRepo.save(role);
    }

    public void delete(Role role) {
        roleRepo.delete(role);
    }
}
