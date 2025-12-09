package com.openflights.openflights_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AeroportoNaoEncontradoException extends RuntimeException {

    public AeroportoNaoEncontradoException(String iata) {
        super("Aeroporto com código IATA '" + iata + "' não encontrado.");
    }
}