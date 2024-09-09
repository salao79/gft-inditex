package com.gft.inditex.domain.service;

import com.gft.inditex.application.ports.input.PrecioInputPort;
import com.gft.inditex.application.ports.output.PrecioOutputPort;
import com.gft.inditex.domain.constants.Constantes;
import com.gft.inditex.domain.exception.NotFoundException;
import com.gft.inditex.domain.exception.ValidationException;
import com.gft.inditex.domain.model.PrecioDomainModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class PrecioService implements PrecioInputPort {

    @Autowired
    @Qualifier("JPAAdapter")
    private PrecioOutputPort precioOutputPort;

    @Override
    // Añado excepciones aqui para permitir que cada adaptador las gestione de diferente manera
    public PrecioDomainModel getPrecio(Integer brandId, Integer productId, OffsetDateTime fechaAplicacion) throws ValidationException, NotFoundException {
        this.validarParametrosEntrada(brandId, productId, fechaAplicacion);
        List<PrecioDomainModel> candidatos = precioOutputPort.getPrecio(brandId, productId, fechaAplicacion);
        return this.filtrar(candidatos);
    }

    protected PrecioDomainModel filtrar(List<PrecioDomainModel> candidatos) throws NotFoundException {
        return candidatos
                .stream()
                .max(Comparator
                        .comparingInt(PrecioDomainModel::getPriority) // Cogemos el que tenga mayor prioridad
                        .thenComparing(PrecioDomainModel::getStartDate)) // Y si aun hay colision, cogemos el que tenga la fecha de comienzo mas reciente
                // (Validacion innecesaria con estos datos de prueba, pero lo hago para ilustrar lo facil que seria añadir mas reglas)
                .orElseThrow(() -> new NotFoundException(Constantes.PRECIO_NO_ENCONTRADO));
    }

    private void validarParametrosEntrada(Integer brandId, Integer productId, OffsetDateTime fechaAplicacion) throws ValidationException {
        if(brandId == null) {
            throw new ValidationException(Constantes.NULL_BRAND_ID);
        }
        if(productId == null) {
            throw new ValidationException(Constantes.NULL_PRODUCT_ID);
        }
        if(fechaAplicacion == null) {
            throw new ValidationException(Constantes.NULL_FECHA_APP);
        }
    }
}
