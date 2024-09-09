package com.gft.inditex.application.ports.input;

import com.gft.inditex.domain.exception.NotFoundException;
import com.gft.inditex.domain.exception.ValidationException;
import com.gft.inditex.domain.model.PrecioDomainModel;

import java.time.OffsetDateTime;


public interface PrecioInputPort {

    /**
     * Consulta el precio aplicable de un producto para una cadena específica en una fecha y hora dada.
     *
     * @param brandId         Identificador de la cadena de la tienda (por ejemplo, 1 para ZARA).
     * @param productId       Identificador del producto.
     * @param fechaAplicacion Fecha y hora en la que se desea aplicar la consulta.
     * @return Un Optional que contiene el PrecioDTO si se encuentra, o vacío si no.
     */
    PrecioDomainModel getPrecio(Integer brandId, Integer productId, OffsetDateTime fechaAplicacion) throws ValidationException, NotFoundException;


}
