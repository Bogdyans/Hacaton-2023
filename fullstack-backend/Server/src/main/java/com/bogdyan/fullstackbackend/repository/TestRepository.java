package com.bogdyan.fullstackbackend.repository;

import com.bogdyan.fullstackbackend.model.Answer;
import com.bogdyan.fullstackbackend.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Integer> {
    List<Test> findAllByOrderByTestIdAsc();
}
