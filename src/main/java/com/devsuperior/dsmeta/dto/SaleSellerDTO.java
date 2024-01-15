package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projection.SaleSellerProjection;

public class SaleSellerDTO {

	private Long id;
	private LocalDate date;
	private Double amount;
	private String sellerName;
	
	public SaleSellerDTO(Long id, Double amount, LocalDate date, String sellerName) {
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.sellerName = sellerName;
	}
	
	public SaleSellerDTO(Sale entity) {
		id = entity.getId();
		date = entity.getDate();
		amount = entity.getAmount();
		sellerName = entity.getSeller().getName();
	}

	public SaleSellerDTO(SaleSellerProjection saleSellerProjection) {
		this.id = saleSellerProjection.getId();
		this.amount = saleSellerProjection.getAmount();
		this.date = saleSellerProjection.getDate();
		this.sellerName = saleSellerProjection.getSellerName();
	}

	public Long getId() {
		return id;
	}

	public Double getAmount() {
		return amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getSellerName() {
		return sellerName;
	}
}
