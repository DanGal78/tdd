package com.academiadodesenvolvedor.tdd.services.contratos;

import com.academiadodesenvolvedor.tdd.models.Carro;

import java.util.List;

public interface CarroServiceContrato {

    public Carro salvar(Carro carro);

    public List<Carro> listarCarros();

    List<Carro> listarCarrosPorMarca(String marca);

    Carro buscarPorId(long id);

    Carro atualizarCarro(Carro carro);

    void apagarCarro(long id);



}
