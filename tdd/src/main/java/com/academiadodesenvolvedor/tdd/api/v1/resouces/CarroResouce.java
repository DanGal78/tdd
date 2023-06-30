package com.academiadodesenvolvedor.tdd.api.v1.resouces;

import com.academiadodesenvolvedor.tdd.api.dtos.CarroDTO;
import com.academiadodesenvolvedor.tdd.api.form.CreateCarroForm;
import com.academiadodesenvolvedor.tdd.api.form.UpdateCarroForm;
import com.academiadodesenvolvedor.tdd.models.Carro;
import com.academiadodesenvolvedor.tdd.services.CarroService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/carros")
@RestController
public class CarroResouce {

    @Autowired
    private CarroService service;

    @PostMapping
    public ResponseEntity<CarroDTO> cadastraCarro(@RequestBody CreateCarroForm form){
        Carro carro = this.service.salvar(form.convert());
        return new ResponseEntity<>(carro.toDTO(), HttpStatus.CREATED);


    }
    @GetMapping
    public ResponseEntity<Page<CarroDTO>> listarCarros(Pageable page){
        Page<Carro> carroPage = this.service.listarCarros(page);
        Page<CarroDTO> carroDTOS = carroPage.map(Carro::toDTO);

        return new ResponseEntity<>(carroDTOS, HttpStatus.OK);


    }
    @PutMapping("/{id}")
    public ResponseEntity<CarroDTO> updateCarro(@PathVariable Long id, @RequestBody UpdateCarroForm form){
        Carro carro = this.service.buscarPorId(id);
        carro = form.update(carro);
        carro = this.service.atualizarCarro(carro);

        return new ResponseEntity<>(carro.toDTO(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroDTO> listarCarroPorId(@PathVariable Long id){
        Carro carro = this.service.buscarPorId(id);

        return new ResponseEntity<>(carro.toDTO(),HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> apagaCarro(@PathVariable Long id) {
        this.service.buscarPorId(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
