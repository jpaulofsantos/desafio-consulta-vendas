package com.devsuperior.dsmeta.projection;

import com.devsuperior.dsmeta.entities.Seller;

import java.time.LocalDate;

public interface SaleSellerProjection {

    Long getId();

    Double getAmount();

    LocalDate getDate();

    Seller getSeller();

}
