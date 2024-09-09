package com.gft.inditex.infrastructure.adapters.input.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.OffsetDateTime;

/**
 * Precio
 */

@Data
public class PrecioDTO {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("brandId")
  private Integer brandId;

  @JsonProperty("priceList")
  private Integer priceList;

  @JsonProperty("startDate")
  private OffsetDateTime startDate;

  @JsonProperty("endDate")
  private OffsetDateTime endDate;

  @JsonProperty("price")
  private Float price;

  @JsonProperty("currency")
  private String currency;

}
