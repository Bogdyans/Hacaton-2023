package com.bogdyan.fullstackbackend.repository;

import com.bogdyan.fullstackbackend.model.Answer;
import com.bogdyan.fullstackbackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findAllByOrderByRoleId();
}
