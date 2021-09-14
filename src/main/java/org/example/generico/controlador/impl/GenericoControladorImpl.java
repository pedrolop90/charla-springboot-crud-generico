package org.example.generico.controlador.impl;

import org.example.generico.controlador.GenericoControlador;
import org.example.generico.dto.GenericoDto;
import org.example.generico.dto.GenericoRespuesta;
import org.example.generico.servicio.GenericoServicio;

import java.util.stream.Stream;

public abstract class GenericoControladorImpl<DTO extends GenericoDto<ID>, ID>
        implements GenericoControlador<DTO, ID> {

    @Override
    public GenericoRespuesta<DTO> guardar(DTO peticion) {
        return new GenericoRespuesta<DTO>(
                obtenerServicio().guardar(peticion)
        );
    }

    @Override
    public GenericoRespuesta<DTO> actualizar(DTO peticion) {
        return new GenericoRespuesta<DTO>(
                obtenerServicio().actualizar(peticion)
        );
    }

    @Override
    public void eliminarById(ID id) {
        obtenerServicio().eliminar(id);
    }

    @Override
    public GenericoRespuesta<DTO> obtenerPorId(ID id) {
        return obtenerServicio()
                .buscarPorId(id)
                .map(dto -> new GenericoRespuesta<DTO>(
                        dto
                ))
                .orElse(null);
    }

    @Override
    public GenericoRespuesta<Stream<DTO>> obtenerTodos() {
        Stream<DTO> respuesta = obtenerServicio().buscarTodo();
        return new GenericoRespuesta<Stream<DTO>>(respuesta);
    }

    protected abstract GenericoServicio<DTO, ID> obtenerServicio();
}
