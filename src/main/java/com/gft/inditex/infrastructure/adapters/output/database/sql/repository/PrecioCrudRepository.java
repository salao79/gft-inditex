package com.gft.inditex.infrastructure.adapters.output.database.sql.repository;

import com.gft.inditex.infrastructure.adapters.output.database.sql.model.PrecioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface PrecioCrudRepository extends CrudRepository<PrecioEntity, Long> {

    List<PrecioEntity> findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Integer brandId, Integer productId, OffsetDateTime startDate, OffsetDateTime endDate);
}
