package com.empresa.excusas.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ExcusaControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void crearYListarExcusas() throws Exception {
        // Crear excusa
        mockMvc.perform(post("/api/excusas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"legajoEmpleado\":1001,\"descripcion\":\"Me quede dormido\",\"tipoExcusa\":\"TRIVIAL\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.descripcion").value("Me quede dormido"));

        // Listar excusas
        mockMvc.perform(get("/api/excusas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].descripcion").value(org.hamcrest.Matchers.hasItem("Me quede dormido")));
    }
} 