package org.example.persona.servicio.impl;

import lombok.RequiredArgsConstructor;
import org.example.generico.mapper.GenericoMapper;
import org.example.generico.servicio.impl.GenericoServicioImpl;
import org.example.persona.dao.PersonaDao;
import org.example.persona.dto.PersonaDto;
import org.example.persona.entidad.PersonaEntidad;
import org.example.persona.mapper.PersonaMapper;
import org.example.persona.servicio.PersonaServicio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonaServicioImpl
        extends GenericoServicioImpl<PersonaEntidad, PersonaDto, Long>
        implements PersonaServicio {

    private final PersonaDao personaDao;
    private final PersonaMapper mapper;

    @Override
    protected CrudRepository<PersonaEntidad, Long> getDao() {
        return personaDao;
    }

    @Override
    protected GenericoMapper<PersonaEntidad, PersonaDto> getMapper() {
        return mapper;
    }
}
