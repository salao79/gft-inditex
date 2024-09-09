package com.gft.inditex.domain;

import com.gft.inditex.application.ports.input.PrecioInputPort;
import com.gft.inditex.infrastructure.adapters.output.database.sql.repository.PrecioCrudRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
public class PrecioIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PrecioCrudRepository precioCrudRepository;

    @Mock
    private PrecioInputPort precioInputPort;


    // TESTS QUE PEDIA EL TEXTO

    @Test
    public void test1_Precio_a_las_10_00_del_14() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/precio")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("fechaAplicacion", "2020-06-14T10:00:00+00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    public void test2_Precio_a_las_16_00_del_14() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/precio")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("fechaAplicacion", "2020-06-14T16:00:00+00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(25.45));
    }

    @Test
    public void test3_Precio_a_las_21_00_del_14() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/precio")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("fechaAplicacion", "2020-06-14T21:00:00+00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    public void test4_Precio_a_las_10_00_del_15() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/precio")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("fechaAplicacion", "2020-06-15T10:00:00+00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(30.50));
    }

    @Test
    public void test5_Precio_a_las_21_00_del_16() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/precio")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("fechaAplicacion", "2020-06-16T21:00:00+00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(38.95));
    }

}