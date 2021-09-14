package org.example.persona.controlador;

import org.example.generico.controlador.impl.GenericoControladorImpl;
import org.example.generico.servicio.GenericoServicio;
import org.example.persona.dto.PersonaDto;
import org.example.persona.servicio.PersonaServicio;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("personas")
public class PersonaControlador extends GenericoControladorImpl<PersonaDto, Long> {

    private final PersonaServicio personaServicio;

    public PersonaControlador(@Lazy PersonaServicio personaServicio) {
        this.personaServicio = personaServicio;
    }

    @Override
    protected GenericoServicio<PersonaDto, Long> obtenerServicio() {
        return personaServicio;
    }
}
