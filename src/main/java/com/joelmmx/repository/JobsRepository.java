package com.joelmmx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joelmmx.entity.Jobs;

public interface JobsRepository extends JpaRepository<Jobs,Integer>{
	boolean existsById(Integer id);
}
