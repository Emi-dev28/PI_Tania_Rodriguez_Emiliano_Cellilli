package com.example.PI_Emi_Tania.services.implementaciones;

import com.example.PI_Emi_Tania.dto.DomicilioDto;
import com.example.PI_Emi_Tania.dto.OdontologoDto;
import com.example.PI_Emi_Tania.dto.PacienteDto;
import com.example.PI_Emi_Tania.dto.TurnoDto;
import com.example.PI_Emi_Tania.entity.Domicilio;
import com.example.PI_Emi_Tania.entity.Odontologo;
import com.example.PI_Emi_Tania.entity.Paciente;
import com.example.PI_Emi_Tania.entity.Turno;
import com.example.PI_Emi_Tania.exceptions.BadRequestException;
import com.example.PI_Emi_Tania.exceptions.ResourceNotFoundException;
import com.example.PI_Emi_Tania.utils.LocalDateTimeAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TurnoServiceTest {
    @Autowired
    private TurnoService turnoService;
     @Autowired
     private PacienteService pacienteService;
     @Autowired
     private OdontologoService odontologoService;
     @Autowired
     private ObjectMapper objectMapper;

    @Test
    @Order(1)
    void deberiaRegistrarUnTurno() throws BadRequestException, ResourceNotFoundException {
        LocalDate fecha = LocalDate.now();
        Odontologo odontologo = new Odontologo("Ad-123423423","German", "Gonzalez");
        Domicilio dom = new Domicilio(1L,"Roca", 1423, "Moron", "Buenos Aires");
        Paciente paciente = new Paciente(1L,"Emiliano", "Cellilli", "42723977",fecha , dom);
        PacienteDto pacienteDto = pacienteService.guardar(paciente);
        OdontologoDto domicilioDto = odontologoService.registrarOdontologo(odontologo);
        TurnoDto turnoDto = turnoService.guardar(new Turno(paciente,odontologo,LocalDateTime.of(LocalDate.of(2024,10,15), LocalTime.of(15,30))));
        assertNotNull(turnoDto.getId());
        assertNotNull(turnoDto);
    }
    @Test
    @Order(2)
    void deberiaListarTurnos(){
        turnoService.listar();

    }
}