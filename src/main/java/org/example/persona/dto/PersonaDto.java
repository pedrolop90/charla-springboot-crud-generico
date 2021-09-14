package org.example.persona.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.generico.dto.GenericoDto;

@Getter
@Setter
@NoArgsConstructor
public class PersonaDto extends GenericoDto<Long> {

    private String nombre;

}
