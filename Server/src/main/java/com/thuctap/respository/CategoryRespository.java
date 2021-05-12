package com.thuctap.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thuctap.entity.category;

public interface CategoryRespository extends JpaRepository<category, Integer> {

}
