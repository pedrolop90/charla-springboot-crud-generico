package org.example.generico.validaciones;

import lombok.Getter;

@Getter
public class ValidacionException extends RuntimeException {

    private final ErrorValidacionRespuesta errorValidacionRespuesta;

    public ValidacionException(ErrorValidacionRespuesta errorValidacionRespuesta) {
        this.errorValidacionRespuesta = errorValidacionRespuesta;
    }

}
