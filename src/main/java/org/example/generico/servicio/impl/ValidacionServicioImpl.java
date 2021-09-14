package org.example.generico.servicio.impl;

import lombok.RequiredArgsConstructor;
import org.example.generico.dto.GenericoDto;
import org.example.generico.servicio.ValidacionServicio;
import org.example.generico.validaciones.ErrorValidacion;
import org.example.generico.validaciones.ErrorValidacionRespuesta;
import org.example.generico.validaciones.TipoValidacion;
import org.example.generico.validaciones.Validacion;
import org.example.generico.validaciones.ValidacionException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Lazy
@RequiredArgsConstructor
public class ValidacionServicioImpl implements ValidacionServicio {

    private final List<Validacion> validaciones;
    private Map<Class<? extends GenericoDto>, Map<TipoValidacion, List<Validacion>>> mapaValidaciones;

    @PostConstruct
    public void asignacion() {
        mapaValidaciones = generar();
    }

    @Override
    public Map<Class<? extends GenericoDto>, Map<TipoValidacion, List<Validacion>>> generar() {
        Map<Class<? extends GenericoDto>, Map<TipoValidacion, List<Validacion>>> mapaRespuesta = new HashMap<>();
        validaciones.forEach(validacion -> {
            Class<? extends GenericoDto> claseGenericoDto = sacarGenericoDto(validacion);
            List<TipoValidacion> tipoValidaciones = validacion.tipoValidacion();
            tipoValidaciones.forEach(tipoValidacion -> {
                mapaRespuesta
                        .computeIfAbsent(claseGenericoDto, aClass -> new HashMap<TipoValidacion, List<Validacion>>())
                        .computeIfAbsent(tipoValidacion, tipoValidacion1 -> new ArrayList<>())
                        .add(validacion);
            });
        });
        return mapaRespuesta;
    }

    @Override
    public List<Validacion> procesarValidacion(GenericoDto objetoAValidar, TipoValidacion tipoValidacion) {
        Class<? extends GenericoDto> claseAValidar = sacarClaseGenerica(objetoAValidar);
        return mapaValidaciones
                .computeIfAbsent(claseAValidar, aClass -> new HashMap<TipoValidacion, List<Validacion>>())
                .computeIfAbsent(tipoValidacion, tipoValidacion1 -> new ArrayList<>());
    }

    @Override
    public void validarClase(GenericoDto objetoAValidar, TipoValidacion tipoValidacion) {
        List<ErrorValidacion> erroresValidaciones = new ArrayList<>();
        procesarValidacion(objetoAValidar, tipoValidacion).forEach(validacion -> {
            erroresValidaciones
                    .addAll(
                            validacion.validar(objetoAValidar)
                    );
        });
        if (!erroresValidaciones.isEmpty()) {
            throw new ValidacionException(
                    ErrorValidacionRespuesta
                            .builder()
                            .validaciones(erroresValidaciones)
                            .build()
            );
        }
    }


    private Class<? extends GenericoDto> sacarClaseGenerica(GenericoDto objetoAValidar) {
        return objetoAValidar.getClass();
    }

    private Class<? extends GenericoDto> sacarGenericoDto(Validacion validacion) {
        return (Class) ((ParameterizedType) validacion.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
    }
}
