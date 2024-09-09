package com.gft.inditex.application.ports.output;

import com.gft.inditex.domain.model.PrecioDomainModel;

import java.time.OffsetDateTime;
import java.util.List;

public interface PrecioOutputPort {

    List<PrecioDomainModel> getPrecio(Integer brandId, Integer productId, OffsetDateTime fechaAplicacion);

}
