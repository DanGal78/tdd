package com.academiadodesenvolvedor.tdd.api.form;


import com.academiadodesenvolvedor.tdd.models.Carro;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateCarroForm {
    private String marca;
    private String modelo;
    private int ano;
    private String combustivel;
    private String cor;

    public Carro convert(){
        return new Carro(
                this.getMarca(),
                this.getModelo(),
                this.getAno(),
                this.getCombustivel(),
                this.getCor()
        );
    }
}
