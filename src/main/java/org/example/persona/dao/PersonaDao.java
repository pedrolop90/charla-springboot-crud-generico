package org.example.persona.dao;

import org.example.persona.entidad.PersonaEntidad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaDao extends CrudRepository<PersonaEntidad, Long> {
}
