package com.bogdyan.fullstackbackend.repository;

import com.bogdyan.fullstackbackend.model.Answer;
import com.bogdyan.fullstackbackend.model.UGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UGroupRepository extends JpaRepository<UGroup, Integer> {

}
