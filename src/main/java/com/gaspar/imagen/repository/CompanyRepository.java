package com.gaspar.imagen.repository;

import com.gaspar.imagen.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
}
