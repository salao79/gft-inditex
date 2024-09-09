package com.gft.inditex.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrecioDomainModel {

    private Long id;
    private Integer brandId;
    private Integer priceList;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private Integer priority;
    private Float price;
    private String currency;

}
