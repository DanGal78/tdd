package com.academiadodesenvolvedor.tdd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.academiadodesenvolvedor.tdd.models.Carro;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long>{
    List<Carro> findAllByMarca(String marca);
}
