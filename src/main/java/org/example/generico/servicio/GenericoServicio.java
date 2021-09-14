package org.example.generico.servicio;

import org.example.generico.dto.GenericoDto;

import java.util.Optional;
import java.util.stream.Stream;

public interface GenericoServicio
        <DTO extends GenericoDto<ID>,
                ID> {

    DTO guardar(DTO dto);

    DTO actualizar(DTO dto);

    void eliminar(ID id);

    Optional<DTO> buscarPorId(ID id);

    Stream<DTO> buscarTodo();
}
