package com.joelmmx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joelmmx.entity.Genders;

public interface GendersRepository extends JpaRepository<Genders,Integer>{
	boolean existsById(Integer id);
}
