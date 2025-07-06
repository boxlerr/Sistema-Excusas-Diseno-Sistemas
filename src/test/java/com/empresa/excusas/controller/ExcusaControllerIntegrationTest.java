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
        mockMvc.perform(post("/excusas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"empleadoLegajo\":1001,\"motivo\":\"Me quede dormido\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.motivo").value("Me quede dormido"));

        // Listar excusas
        mockMvc.perform(get("/excusas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].motivo").value("Me quede dormido"));
    }
} 