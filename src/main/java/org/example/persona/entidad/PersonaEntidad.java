package org.example.persona.entidad;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.generico.entidad.GenericoEntidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PERSONA")
@Getter
@Setter
@NoArgsConstructor
public class PersonaEntidad implements GenericoEntidad<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

}
