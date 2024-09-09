package com.gft.inditex.infrastructure.adapters.output.database.sql.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;


@Entity
@Table(name = "prices")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PrecioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_id", nullable = false)
    private Integer brandId;

    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Column(name = "price_list", nullable = false)
    private Integer priceList;

    @Column(name = "start_date", nullable = false)
    private OffsetDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private OffsetDateTime endDate;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "priority", nullable = false)
    private Integer priority;



}
