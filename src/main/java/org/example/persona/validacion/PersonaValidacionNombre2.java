package org.example.persona.validacion;

import org.example.generico.validaciones.ErrorValidacion;
import org.example.generico.validaciones.TipoValidacion;
import org.example.generico.validaciones.Validacion;
import org.example.persona.dto.PersonaDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PersonaValidacionNombre2 implements Validacion<PersonaDto> {
    @Override
    public List<ErrorValidacion> validar(PersonaDto dto) {
        List<ErrorValidacion> errorValidacions = new ArrayList<>();
        if (!dto.getNombre().equals("pedro")) {
            errorValidacions.add(
                    ErrorValidacion
                            .builder()
                            .nombre("El nombre no es correcto2")
                            .descripcion("debe especificar un nombre como pedro")
                            .build()
            );
        }

        return errorValidacions;
    }

    @Override
    public List<TipoValidacion> tipoValidacion() {
        return Arrays.asList(TipoValidacion.CREAR, TipoValidacion.ACTUALIZAR);
    }
}
