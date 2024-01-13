package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.projection.SaleSellerProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleSellerDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleSellerDTO> findById(@PathVariable Long id) {
		SaleSellerDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	/*@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleSellerDTO>> getReport(
			@RequestParam(value = "minDate", defaultValue = "") String dateMin,
			@RequestParam(value = "maxDate", defaultValue = "")String dateFin,
			@RequestParam(value = "name", defaultValue = "") String name,
			Pageable pageable) {
		Page<SaleSellerDTO> result = service.searchSaleSeller(dateMin, dateFin, name, pageable);

		return ResponseEntity.ok(result);
	}*/

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleSellerDTO>> getReport(
			@RequestParam(value = "minDate", defaultValue = "") String dateMin,
			@RequestParam(value = "maxDate", defaultValue = "")String dateFin,
			@RequestParam(value = "name", defaultValue = "") String name,
			Pageable pageable) {
		Page<SaleSellerProjection> result = service.searchSaleSellerProjection(dateMin, dateFin, name, pageable);
		Page<SaleSellerDTO> result2 = result.map(x-> new SaleSellerDTO(x));

		return ResponseEntity.ok(result2);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SaleSummaryDTO>> getSummary(
			@RequestParam(value = "minDate", defaultValue = "") String dateMin,
			@RequestParam(value = "maxDate", defaultValue = "")String dateFin
	) {
		List<SaleSummaryDTO> result = service.searchSellerSummary(dateMin, dateFin);
		return ResponseEntity.ok(result);
	}
}
