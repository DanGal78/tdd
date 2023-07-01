package com.academiadodesenvolvedor.tdd.services;

import com.academiadodesenvolvedor.tdd.exceptions.NotFoundException;
import com.academiadodesenvolvedor.tdd.models.Carro;
import com.academiadodesenvolvedor.tdd.repositories.CarroRepository;
import com.academiadodesenvolvedor.tdd.services.contratos.CarroServiceContrato;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CarroServiceTest {

    @SpyBean
    private  CarroServiceContrato carroService;
    @MockBean
    private CarroRepository repository;


    @Test
    @DisplayName("Testa se a service de carro esta salvando corretamente")
    public void salvarCarroTest(){

        Carro carro =  this.criarCarro();
        Carro carroSaved = this.criarCarro();
        carroSaved.setId(1L);
        Mockito.when(this.repository.save(carro)).thenReturn(carroSaved);
        carro = carroService.salvar(carro);

        Assertions.assertNotNull(carro.getId());
        Assertions.assertNotEquals(0L, carro.getId());

    }
    @Test
    @DisplayName("Testa se a service de carro listando corretamente")
    public void listarCarrosTest(){
        Carro carroSaved = this.criarCarro();
        carroSaved.setId(1L);
        Mockito.when(this.repository.findAll()).thenReturn(Arrays.asList(carroSaved));
        List<Carro> listagem = carroService.listarCarros();
        Assertions.assertTrue(listagem.size()!= 0);

        Carro carro = listagem.get(0);
        Assertions.assertNotNull(carro.getId());
        Assertions.assertNotEquals(0L, carro.getId());

    }

    @Test
    @DisplayName("Testa se a service de carro está listando por modelos")
    public void listaCarroPorModeloTest(){

        Mockito.when(this.repository.findAllByMarca("Volkswagen"))
                .thenReturn(Arrays.asList(this.criarCarro()));
        List<Carro> listagem = carroService.listarCarrosPorMarca("Volkswagen");

        Assertions.assertTrue(listagem.size() > 0);
    }

    @Test
    @DisplayName("Testa se a service de carro está buscando com Id")
    public void buscaCarroPorId() {

        Optional<Carro> carroOptional = Optional.of(this.criarCarro());
        Mockito.when(this.repository.findById(1L)).thenReturn(carroOptional);

        Carro carro = carroService.buscarPorId(1L);

        Assertions.assertNotNull(carro);
        Assertions.assertNotEquals(0L, carro.getId());
    }

    @Test
    @DisplayName("Testa se a service de carro está Atualizando")
    public void atualizarCarroTest() {

        Carro carroSaved = this.criarCarro();
        carroSaved.setId(1L);
        Mockito.when(this.repository.findById(1L)).thenReturn(Optional.of(carroSaved));

        Carro carro = carroService.buscarPorId(1L);
        carro.setCor("Preta");

        Mockito.when(this.repository.save(carro)).thenReturn(carro);
        carro = carroService.atualizarCarro(carro);
        Mockito.verify(this.repository,Mockito.times(1)).save(carro);

        carro = carroService.atualizarCarro(carro);

        Assertions.assertNotEquals("vermelho", carro.getCor());
    }

    @Test
    @DisplayName("Testa se a service de apagando corretamente")
    public void apagaCarroTest() {
        Carro carroSaved = this.criarCarro();
        carroSaved.setId(1L);
        Mockito.when(this.repository.findById(1L))
                        .thenReturn(Optional.of(carroSaved));

         carroService.apagarCarro(1L);

         Mockito.when(this.repository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class,() -> {
             carroService.buscarPorId(1L);
        });




    }
    @Test
    @DisplayName("Testa se a sobrecarga do listar carro esta funcionando corretamente")
    public  void  listarCarrosPaginadoTest(){
        Pageable page = PageRequest.of(0,15);
        Mockito.when(this.repository.findAll(page)).thenReturn(new PageImpl<>(Arrays.asList(this.criarCarro())));

        Page<Carro> carroPage = carroService.listarCarros(page);

        Assertions.assertTrue(carroPage.hasContent());
    }
    @Test
    @DisplayName("Testa se a busca pode lancar exceçao")
    public  void  deveLancarExecao(){
        Assertions.assertThrows(NotFoundException.class, () ->{
            this.carroService.buscarPorId(0L);
        });
    }



    @BeforeEach
    public  void cadastraCarro(){
        Carro carro =  new Carro("Volkswagen", "Gol", 2010,"Flex", "Vermelho");

        carro = carroService.salvar(carro);

    }

    private  Carro criarCarro(){
        return new Carro("Volkswagen", "Gol", 2010,"Flex", "Vermelho");

    }
}
