package org.example.generico.validaciones;

import org.example.generico.dto.GenericoDto;

import java.util.List;

public interface Validacion<DTO extends GenericoDto> {

    List<ErrorValidacion> validar(DTO dto);

    List<TipoValidacion> tipoValidacion();
}
