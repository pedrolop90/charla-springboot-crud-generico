package org.example.generico.servicio;

import org.example.generico.dto.GenericoDto;
import org.example.generico.validaciones.TipoValidacion;
import org.example.generico.validaciones.Validacion;

import java.util.List;
import java.util.Map;

public interface ValidacionServicio {

    Map<Class<? extends GenericoDto>, Map<TipoValidacion, List<Validacion>>> generar();

    List<Validacion> procesarValidacion(GenericoDto objetoAValidar, TipoValidacion tipoValidacion);

    void validarClase(GenericoDto objetoAValidar, TipoValidacion tipoValidacion);

}
