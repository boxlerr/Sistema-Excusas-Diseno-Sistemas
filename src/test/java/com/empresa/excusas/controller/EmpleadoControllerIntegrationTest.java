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
public class EmpleadoControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void crearYListarEmpleados() throws Exception {
        // Crear empleado
        mockMvc.perform(post("/empleados")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\":\"Juan Perez\",\"email\":\"juan@empresa.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Juan Perez"));

        // Listar empleados
        mockMvc.perform(get("/empleados"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Juan Perez"));
    }
} 