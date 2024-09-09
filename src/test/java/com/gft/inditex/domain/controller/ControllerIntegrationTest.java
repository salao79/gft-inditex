package com.gft.inditex.domain.controller;

import com.gft.inditex.domain.constants.Constantes;
import com.gft.inditex.infrastructure.adapters.output.database.sql.repository.PrecioCrudRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") // Para usar la configuración de H2
public class ControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PrecioCrudRepository precioCrudRepository;

    @Test
    public void test1_OK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/precio")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("fechaAplicacion", "2020-06-14T10:00:00+00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    public void test2_Precio_Invalid_Parameters_BadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/precio")
                        .param("productId", "35455")
                        .param("fechaAplicacion", "2020-06-14T10:00:00+00:00"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(Constantes.NULL_BRAND_ID));
    }

    @Test
    public void test3_Precio_NotFound_404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/precio")
                        .param("brandId", "1")
                        .param("productId", "99999") // Producto no existente
                        .param("fechaAplicacion", "2020-06-14T10:00:00+00:00"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(Constantes.PRECIO_NO_ENCONTRADO));
    }


}
