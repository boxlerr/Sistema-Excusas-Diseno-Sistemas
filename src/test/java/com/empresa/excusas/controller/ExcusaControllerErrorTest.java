package com.empresa.excusas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@SpringBootTest
@AutoConfigureMockMvc
public class ExcusaControllerErrorTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void crearExcusa_tipoInvalido_retornaBadRequest() throws Exception {
        String excusaJson = "{ \"legajoEmpleado\": 1001, \"descripcion\": \"Test\", \"tipoExcusa\": \"NO_EXISTE\" }";
        mockMvc.perform(post("/api/excusas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(excusaJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Tipo de excusa inválido: NO_EXISTE"));
    }

    @Test
    void eliminarExcusas_sinFechaLimite_retornaBadRequest() throws Exception {
        mockMvc.perform(delete("/api/excusas/eliminar"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Debe proporcionar fechaLimite"));
    }
} 