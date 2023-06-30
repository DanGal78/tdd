package com.academiadodesenvolvedor.tdd.models;


import com.academiadodesenvolvedor.tdd.api.dtos.CarroDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carros")
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String marca;
    @NonNull
    private String modelo;
    @NonNull
    private int ano;
    @NonNull
    private String combustivel;
    @NonNull
    private String cor;

    public CarroDTO toDTO(){
        return new CarroDTO(
                this.getId(),
                this.getMarca(),
                this.getModelo(),
                this.getAno(),
                this.getCombustivel(),
                this.getCor()
        );
    }

}
