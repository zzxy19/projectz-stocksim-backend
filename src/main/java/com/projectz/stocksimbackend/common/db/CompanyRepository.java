package com.projectz.stocksimbackend.common.db;

import com.projectz.stocksimbackend.common.db.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, String> {
}
