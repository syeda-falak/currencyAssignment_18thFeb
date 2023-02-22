package com.example.currencyconvertion.repository;

import com.example.currencyconvertion.model.AuditInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditInfoRepository extends JpaRepository<AuditInfo,Integer> {
}
