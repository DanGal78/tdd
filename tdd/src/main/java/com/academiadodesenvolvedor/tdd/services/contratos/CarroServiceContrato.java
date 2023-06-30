package com.academiadodesenvolvedor.tdd.services.contratos;

import com.academiadodesenvolvedor.tdd.models.Carro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarroServiceContrato {

    Carro salvar(Carro carro);

    List<Carro> listarCarros();

    Page<Carro> listarCarros(Pageable page);

    List<Carro> listarCarrosPorMarca(String marca);

    Carro buscarPorId(long id);

    Carro atualizarCarro(Carro carro);

    void apagarCarro(long id);



}
