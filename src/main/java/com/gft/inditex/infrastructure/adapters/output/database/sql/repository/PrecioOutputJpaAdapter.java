package com.gft.inditex.infrastructure.adapters.output.database.sql.repository;

import com.gft.inditex.application.ports.output.PrecioOutputPort;
import com.gft.inditex.domain.helper.ReflectionUtil;
import com.gft.inditex.domain.model.PrecioDomainModel;
import com.gft.inditex.infrastructure.adapters.output.database.sql.model.PrecioEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository("JPAAdapter")
public class PrecioOutputJpaAdapter implements PrecioOutputPort {

    @Autowired
    private PrecioCrudRepository precioCrudRepository;

    @Override
    public List<PrecioDomainModel> getPrecio(Integer brandId, Integer productId, OffsetDateTime fechaAplicacion) {
        List<PrecioEntity> precios = precioCrudRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId, productId, fechaAplicacion, fechaAplicacion);
        return precios.stream().map(precio -> ReflectionUtil.mapWithReflection(precio, PrecioDomainModel.class)).collect(Collectors.toList());
    }

    @PostConstruct
    public void popularDatos() {
        System.out.println("POPULANDO DATOS...");

        List<PrecioEntity> precioEntities = new ArrayList<>();
        precioEntities.add(new PrecioEntity(null, 1, 35455, 1, OffsetDateTime.parse("2020-06-14T00:00:00+00:00"), OffsetDateTime.parse("2020-12-31T23:59:59+00:00"), 35.50f, "EUR", 0));
        precioEntities.add(new PrecioEntity(null, 1, 35455, 2, OffsetDateTime.parse("2020-06-14T15:00:00+00:00"), OffsetDateTime.parse("2020-06-14T18:30:00+00:00"), 25.45f, "EUR", 1));
        precioEntities.add(new PrecioEntity(null, 1, 35455, 3, OffsetDateTime.parse("2020-06-15T00:00:00+00:00"), OffsetDateTime.parse("2020-06-15T11:00:00+00:00"), 30.50f, "EUR", 1));
        precioEntities.add(new PrecioEntity(null, 1, 35455, 4, OffsetDateTime.parse("2020-06-15T16:00:00+00:00"), OffsetDateTime.parse("2020-12-31T23:59:59+00:00"), 38.95f, "EUR", 1));

        List<PrecioEntity> result = (List<PrecioEntity>) precioCrudRepository.saveAll(precioEntities);

        System.out.println("POPULADOS " + result.size() + " RESULTADOS");
    }

}
