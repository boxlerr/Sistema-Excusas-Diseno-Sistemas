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
public class ProntuarioControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void crearYListarProntuarios() throws Exception {
        // Crear prontuario
        mockMvc.perform(post("/prontuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"empleadoLegajo\":1001,\"motivo\":\"Excusa inverosímil\",\"fecha\":\"2024-07-06\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.motivo").value("Excusa inverosímil"));

        // Listar prontuarios
        mockMvc.perform(get("/prontuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].motivo").value("Excusa inverosímil"));
    }
} 