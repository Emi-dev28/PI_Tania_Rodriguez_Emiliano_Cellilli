package com.example.PI_Emi_Tania.services.implementaciones;

import com.example.PI_Emi_Tania.entity.Odontologo;
import com.example.PI_Emi_Tania.exceptions.BadRequestException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;  //lo inyecto para usarlo para el testeo

    @Test
    @Order(1)
    void deberiaInsertarUnOdontologo() throws BadRequestException {
        // Creación de un objeto Odontologo para insertar
        Odontologo odontologoAInsertar = new Odontologo("AB-466464646464646", "patricia", "Lopez");
        // Llamada al método para registrar el Odontologo y obtener el resultado
        OdontologoDto odontologoDto = odontologoService.registrarOdontologo(odontologoAInsertar);

        // Verifica que el resultado no sea nulo
        Assertions.assertNotNull(odontologoDto);
        // Verifica que el ID del OdontologoDto no sea nulo
        Assertions.assertNotNull(odontologoDto.getId());
    }

    @Test
    @Order(2)
    void cuandoNoSeCumplaElFormatoDeMatricula_noDeberiaInsertarElOdontologo() {
        // Creación de un objeto Odontologo con formato de matrícula incorrecto
        Odontologo odontologo = new Odontologo("3433333", "patricia", "Lopez");

        // Verifica que se lance una ConstraintViolationException al intentar registrar el Odontologo
        Assertions.assertThrows(ConstraintViolationException.class, () -> odontologoService.registrarOdontologo(odontologo));
    }

    @Test
    @Order(3)
    void deberiaListarUnSoloOdontologo() {
        // Obtiene la lista de OdontologoDto
        List<OdontologoDto> odontologosDtos = odontologoService.listarOdontologos();

        // Verifica que la lista tenga un solo elemento
        assertEquals(1, odontologosDtos.size());
    }

    @Test
    @Order(4)
    void deberiaEliminarElOdontologoId1() throws ResourceNotFoundException {
        // Elimina el Odontologo con ID 1
        odontologoService.eliminarOdontologo(1L);

        // Verifica que se lance una ResourceNotFoundException al intentar eliminar nuevamente el mismo Odontologo
        Assertions.assertThrows(ResourceNotFoundException.class, () -> odontologoService.eliminarOdontologo(1L));
    }

    @Test
    @Order(5)
    void deberiaRetornarUnaListaVacia() throws ResourceNotFoundException {
        // Obtiene la lista de OdontologoDto después de eliminar todos los Odontologos
        List<OdontologoDto> odontologosDtos = odontologoService.listarOdontologos();

        // Verifica que la lista esté vacía
        Assertions.assertTrue(odontologosDtos.isEmpty());
    }
}