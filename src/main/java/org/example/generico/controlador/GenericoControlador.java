package org.example.generico.controlador;

import org.example.generico.dto.GenericoDto;
import org.example.generico.dto.GenericoRespuesta;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.stream.Stream;

public interface GenericoControlador<DTO extends GenericoDto<ID>, ID> {

    @PostMapping
    GenericoRespuesta<DTO> guardar(@RequestBody DTO peticion);

    @PutMapping
    GenericoRespuesta<DTO> actualizar(@RequestBody DTO peticion);

    @DeleteMapping("{id}")
    void eliminarById(@PathVariable ID id);

    @GetMapping("{id}")
    GenericoRespuesta<DTO> obtenerPorId(@PathVariable ID id);

    @GetMapping
    GenericoRespuesta<Stream<DTO>> obtenerTodos();
}
