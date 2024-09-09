package com.gft.inditex.infrastructure.adapters.input.rest.controller.precio;

import com.gft.inditex.application.ports.input.PrecioInputPort;
import com.gft.inditex.domain.exception.NotFoundException;
import com.gft.inditex.domain.exception.ValidationException;
import com.gft.inditex.domain.helper.ReflectionUtil;
import com.gft.inditex.infrastructure.adapters.input.rest.model.PrecioDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
public class PrecioApiController implements PrecioApi {

    private static final Logger log = LoggerFactory.getLogger(PrecioApiController.class);

    @Autowired
    private PrecioInputPort precioInputPort;

    public ResponseEntity<PrecioDTO> getPrecio(Integer brandId, Integer productId, OffsetDateTime fechaAplicacion) throws ValidationException, NotFoundException {
        return ResponseEntity
                .ok()
                .body(ReflectionUtil.mapWithReflection(precioInputPort.getPrecio(brandId, productId, fechaAplicacion),PrecioDTO.class));
    }

}
