package org.example.persona.error;

import lombok.Getter;

@Getter
public class Error {

    private final String nombreExcepcion;
    private final String mensaje;
    private final int codigo;

    public Error(String nombreExcepcion, String mensaje, int codigo) {
        this.nombreExcepcion = nombreExcepcion;
        this.mensaje = mensaje;
        this.codigo = codigo;
    }

}
