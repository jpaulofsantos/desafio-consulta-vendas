package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleSellerDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.projection.SaleSellerProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    //@Query("SELECT obj FROM Sale obj WHERE obj.date BETWEEN :dateMin AND :dateFin AND UPPER(obj.seller.name)LIKE UPPER (CONCAT('%', :name, '%'))")
    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleSellerDTO(obj.id, obj.amount, obj.date, obj.seller.name) FROM Sale obj WHERE obj.date BETWEEN :dateMin AND :dateFin AND UPPER(obj.seller.name)LIKE UPPER (CONCAT('%', :name, '%'))")
    Page<SaleSellerDTO> searchSaleSeller(LocalDate dateMin, LocalDate dateFin, String name, Pageable pageable);

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleSummaryDTO(obj.seller.name, SUM(obj.amount)) FROM Sale obj WHERE obj.date BETWEEN :dateMin AND :dateFin GROUP BY obj.seller.name")
    List<SaleSummaryDTO> searchSaleSummary(LocalDate dateMin, LocalDate dateFin);

}
