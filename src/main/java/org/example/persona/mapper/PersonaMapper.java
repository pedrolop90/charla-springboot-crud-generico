package org.example.persona.mapper;

import org.example.generico.mapper.GenericoMapper;
import org.example.persona.dto.PersonaDto;
import org.example.persona.entidad.PersonaEntidad;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonaMapper extends GenericoMapper<PersonaEntidad, PersonaDto> {
}
