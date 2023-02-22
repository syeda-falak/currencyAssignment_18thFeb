package com.example.currencyconvertion.repository;

import com.example.currencyconvertion.model.AuditInfoConversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditInfoConversionRepository extends JpaRepository<AuditInfoConversion,Integer> {
}
