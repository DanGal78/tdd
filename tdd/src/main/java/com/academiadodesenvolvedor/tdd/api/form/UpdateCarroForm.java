package com.academiadodesenvolvedor.tdd.api.form;

import com.academiadodesenvolvedor.tdd.models.Carro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarroForm {
    private String marca;
    private String modelo;
    private int ano;
    private String combustivel;
    private String cor;

    public Carro update(Carro carro){
        if(this.getMarca() != null && ! this.getMarca().isEmpty()) carro.setMarca(this.getMarca());
        if(this.getModelo() != null && ! this.getModelo().isEmpty()) carro.setModelo(this.getModelo());
        if(this.getAno() > 1886) carro.setAno(this.getAno());
        if(this.getCombustivel() != null && ! this.getCombustivel().isEmpty()) carro.setCombustivel(this.getCombustivel());
        if(this.getCor() != null && ! this.getCor().isEmpty()) carro.setCor(this.getCor());
        return carro;
    }
}
