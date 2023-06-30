package com.academiadodesenvolvedor.tdd.services;

import com.academiadodesenvolvedor.tdd.models.Carro;
import com.academiadodesenvolvedor.tdd.repositories.CarroRepository;
import com.academiadodesenvolvedor.tdd.services.contratos.CarroServiceContrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService  implements CarroServiceContrato {
    @Autowired
    private CarroRepository carroRepository;
    @Override
    public Carro salvar(Carro carro) {
        return carroRepository.save(carro);
    }
    @Override
    public List<Carro> listarCarros() {
        return carroRepository.findAll();

    }
    @Override
    public List<Carro> listarCarrosPorMarca(String marca) {
        return carroRepository.findAllByMarca(marca);
    }

    @Override
    public Carro buscarPorId(long id) {
        Optional<Carro> carro = carroRepository.findById(id);
        if (carro.isPresent()) {
            return carro.get();
        }

        return null;
    }
    @Override
    public Carro atualizarCarro(Carro carro) {
        return this.carroRepository.save(carro);
    }

    @Override
    public  void apagarCarro(long id) {
        Carro carro = buscarPorId(id);

        carroRepository.delete(carro);
    }
}
