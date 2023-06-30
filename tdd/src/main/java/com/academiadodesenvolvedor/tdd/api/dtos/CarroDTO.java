package com.academiadodesenvolvedor.tdd.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CarroDTO {
    private Long id;
    private String marca;
    private String modelo;
    private int ano;
    private String combustivel;
    private String cor;
}
