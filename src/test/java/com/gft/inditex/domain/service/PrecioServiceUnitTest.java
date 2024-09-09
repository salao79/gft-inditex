package com.gft.inditex.domain.service;

import com.gft.inditex.application.ports.output.PrecioOutputPort;
import com.gft.inditex.domain.exception.NotFoundException;
import com.gft.inditex.domain.exception.ValidationException;
import com.gft.inditex.domain.model.PrecioDomainModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PrecioServiceUnitTest {

    @Mock
    private PrecioOutputPort precioOutputPort;

    @InjectMocks
    private PrecioService precioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test para getPrecio con valores válidos
    @Test
    public void testGetPrecio_Exito() throws ValidationException, NotFoundException {
        Integer brandId = 1;
        Integer productId = 35455;
        OffsetDateTime fechaAplicacion = OffsetDateTime.now();

        // Simular datos de salida
        List<PrecioDomainModel> mockCandidatos = Arrays.asList(
                new PrecioDomainModel(1L, 1, 1, fechaAplicacion.minusDays(1), fechaAplicacion.plusDays(1), 0, 35.50f, "EUR"),
                new PrecioDomainModel(2L, 1, 2, fechaAplicacion.minusDays(2), fechaAplicacion.plusDays(2), 1, 25.45f, "EUR")
        );

        // Configurar el mock para la llamada de getPrecio
        when(precioOutputPort.getPrecio(brandId, productId, fechaAplicacion)).thenReturn(mockCandidatos);

        // Llamar al método
        PrecioDomainModel resultado = precioService.getPrecio(brandId, productId, fechaAplicacion);

        // Verificar que se devuelve el de mayor prioridad
        assertEquals(1, resultado.getPriority());

        // Verificar que se llamó a getPrecio en el mock
        verify(precioOutputPort).getPrecio(brandId, productId, fechaAplicacion);
    }

    // Test para validar excepción NotFoundException cuando no hay candidatos
    @Test
    public void testGetPrecio_NoCandidatos() {
        Integer brandId = 1;
        Integer productId = 35455;
        OffsetDateTime fechaAplicacion = OffsetDateTime.now();

        // Configurar el mock para devolver lista vacía
        when(precioOutputPort.getPrecio(brandId, productId, fechaAplicacion)).thenReturn(Collections.emptyList());

        // Verificar que se lanza NotFoundException
        assertThrows(NotFoundException.class, () -> {
            precioService.getPrecio(brandId, productId, fechaAplicacion);
        });
    }

    // Test para validar excepción ValidationException cuando brandId es null
    @Test
    public void testValidarParametrosEntrada_BrandIdNull() {
        Integer brandId = null;
        Integer productId = 35455;
        OffsetDateTime fechaAplicacion = OffsetDateTime.now();

        // Verificar que se lanza ValidationException
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            precioService.getPrecio(brandId, productId, fechaAplicacion);
        });

        assertEquals("El campo brandId no puede ser null", exception.getMessage());
    }

    // Test para validar excepción ValidationException cuando productId es null
    @Test
    public void testValidarParametrosEntrada_ProductIdNull() {
        Integer brandId = 1;
        Integer productId = null;
        OffsetDateTime fechaAplicacion = OffsetDateTime.now();

        // Verificar que se lanza ValidationException
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            precioService.getPrecio(brandId, productId, fechaAplicacion);
        });

        assertEquals("El campo productId no puede ser null", exception.getMessage());
    }
    // Test para validar excepción ValidationException cuando fechaAplicacion es null
    @Test
    public void testValidarParametrosEntrada_FechaAplicacionNull() {
        Integer brandId = 1;
        Integer productId = 35455; OffsetDateTime fechaAplicacion = null;

        // Verificar que se lanza ValidationException
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            precioService.getPrecio(brandId, productId, fechaAplicacion);
        });

        assertEquals("El campo fechaAplicacion no puede ser null", exception.getMessage());
    }

    // Test para verificar que se filtra correctamente el de mayor prioridad
    @Test
    public void testFiltrar_MayorPrioridad() throws NotFoundException {
        // Crear una lista de candidatos con diferentes prioridades
        List<PrecioDomainModel> candidatos = Arrays.asList(
                new PrecioDomainModel(1L, 1, 1, OffsetDateTime.now().minusDays(2), OffsetDateTime.now().plusDays(2), 0, 35.50f, "EUR"),
                new PrecioDomainModel(2L, 1, 2, OffsetDateTime.now().minusDays(1), OffsetDateTime.now().plusDays(1), 1, 25.45f, "EUR")
        );

        // Llamar al método filtrar
        PrecioDomainModel resultado = precioService.filtrar(candidatos);

        // Verificar que se devuelve el de mayor prioridad (priority = 1)
        assertEquals(1, resultado.getPriority());
    }

    // Test para verificar que se lanza NotFoundException cuando la lista de candidatos está vacía
    @Test
    public void testFiltrar_NotFoundException() {
        // Lista vacía de candidatos
        List<PrecioDomainModel> candidatos = Collections.emptyList();

        // Verificar que se lanza NotFoundException
        assertThrows(NotFoundException.class, () -> {
            precioService.filtrar(candidatos);
        });
    }
}