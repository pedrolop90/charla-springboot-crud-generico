package org.example.persona.error;

import lombok.extern.log4j.Log4j2;
import org.example.generico.validaciones.ValidacionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.concurrent.ConcurrentHashMap;

@ControllerAdvice
@Log4j2
public class ManejadorErrores extends ResponseEntityExceptionHandler {

    private static final int OCURRIO_UN_ERROR_FAVOR_CONTACTAR_AL_ADMINISTRADOR_CODE = HttpStatus.BAD_REQUEST.value();
    private static final String OCURRIO_UN_ERROR_FAVOR_CONTACTAR_AL_ADMINISTRADOR = "Ocurrió un error favor contactar al administrador.";
    private static final ConcurrentHashMap<String, Integer> CODIGOS_ESTADO = new ConcurrentHashMap<>();

    public ManejadorErrores() {
        CODIGOS_ESTADO.put(
                ValidacionException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value()
        );
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Error> handleAllExceptions(Exception exception) {
        ResponseEntity<Error> resultado;

        String excepcionNombre = exception.getClass().getSimpleName();
        String mensaje = exception.getMessage();
        Integer codigo = CODIGOS_ESTADO.get(excepcionNombre);

        log.error(exception.getMessage(), exception);

        if (codigo != null) {
            Error error = new Error(excepcionNombre, mensaje, codigo);
            resultado = new ResponseEntity<>(error, HttpStatus.valueOf(codigo));
        }
        else {
            Error error = new Error(excepcionNombre,
                    OCURRIO_UN_ERROR_FAVOR_CONTACTAR_AL_ADMINISTRADOR,
                    OCURRIO_UN_ERROR_FAVOR_CONTACTAR_AL_ADMINISTRADOR_CODE);
            resultado = new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resultado;
    }

    @ExceptionHandler(ValidacionException.class)
    public final ResponseEntity<Object> validacionExceptionHanlder(Exception exception, WebRequest request) {
        ValidacionException validacionException = (ValidacionException) exception;
        return super
                .handleExceptionInternal(
                        exception,
                        validacionException.getErrorValidacionRespuesta(),
                        new HttpHeaders(),
                        HttpStatus.BAD_REQUEST,
                        request);
    }

}
