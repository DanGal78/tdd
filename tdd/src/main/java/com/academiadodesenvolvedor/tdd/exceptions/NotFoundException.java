package com.academiadodesenvolvedor.tdd.exceptions;

public class NotFoundException extends  RuntimeException{

    public  NotFoundException(String mensagem){
        super(mensagem);
    }
}
