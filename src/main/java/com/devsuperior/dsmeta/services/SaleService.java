package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.projection.SaleSellerProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleSellerDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleSellerDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleSellerDTO(entity);
	}

	public Page<SaleSellerDTO> searchSaleSeller(String dateMin, String dateFin, String name, Pageable pageable) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate min;
		if (dateMin.equals("")) {
			min = today.minusYears(1L);
		} else {
			min = LocalDate.parse(dateMin);
		}
		LocalDate fin;
		if (dateFin.equals("")) {
			fin = today;
		} else {
			fin = LocalDate.parse(dateFin);
		}
		Page<SaleSellerDTO> result = repository.searchSaleSeller(min, fin, name, pageable);

		return result;
	}

	public List<SaleSummaryDTO> searchSellerSummary(String dateMin, String dateFin) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate min;
		if (dateMin.equals("")) {
			min = today.minusYears(1L);
		} else {
			min = LocalDate.parse(dateMin);
		}
		LocalDate fin;
		if (dateFin.equals("")) {
			fin = today;
		} else {
			fin = LocalDate.parse(dateFin);
		}

		List<SaleSummaryDTO> result = repository.searchSaleSummary(min, fin);

		return result;
	}

	public Page<SaleSellerProjection> searchSaleSellerProjection(String dateMin, String dateFin, String name, Pageable pageable) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate min;
		if (dateMin.equals("")) {
			min = today.minusYears(1L);
		} else {
			min = LocalDate.parse(dateMin);
		}
		LocalDate fin;
		if (dateFin.equals("")) {
			fin = today;
		} else {
			fin = LocalDate.parse(dateFin);
		}
		Page<SaleSellerProjection> result = repository.searchSaleSellerProjection(min, fin, name, pageable);

		return result;
	}
}
