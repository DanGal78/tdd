package com.academiadodesenvolvedor.tdd.api.v1.resources;

import com.academiadodesenvolvedor.tdd.api.form.CreateCarroForm;
import com.academiadodesenvolvedor.tdd.api.form.UpdateCarroForm;
import com.academiadodesenvolvedor.tdd.api.v1.resouces.CarroResouce;
import com.academiadodesenvolvedor.tdd.models.Carro;
import com.academiadodesenvolvedor.tdd.repositories.CarroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class CarroResouceTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private CarroRepository carroRepository;
    static final String RESOURCE = "/api/v1/carros";
    static final MediaType JSONTYPE = MediaType.APPLICATION_JSON;

    @Test
    @DisplayName("Testa se o carro esta sendo cadastrada corretamente")
    public void deveCadastrarUmCarro() throws Exception {
        CreateCarroForm form = new CreateCarroForm("Ford", "Ka", 2007, "Gasolina", "Branco");
        String bodyJson = new ObjectMapper().writeValueAsString(form);

        mvc.perform(this.criaRequest(MockMvcRequestBuilders.post(RESOURCE), bodyJson))
                .andExpect(MockMvcResultMatchers.jsonPath("modelo").value("Ka"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    public MockHttpServletRequestBuilder criaRequest(MockHttpServletRequestBuilder method, String content) {
        return method.content(content).contentType(JSONTYPE).accept(JSONTYPE);

    }

    @Test
    @DisplayName("Testa se o endPoit esta listando corretamente os carro")
    public void deveListarCarros() throws Exception {
        Carro carro = cadastraCarroNoDb();
        mvc.perform(
                this.criaRequest(MockMvcRequestBuilders.get(RESOURCE),"")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("empty").value(false))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("pageSize")));

    }
    @Test
    @DisplayName("Testa se o endPoit esta atualizando corretamente")
    public void deveAtualizarCarros() throws Exception {
        Carro carro = cadastraCarroNoDb();

        UpdateCarroForm form = new UpdateCarroForm();
        form.setCor("Azul");
        String jsonBody = new ObjectMapper().writeValueAsString(form);

        mvc.perform(
                this.criaRequest(
                        MockMvcRequestBuilders.put(RESOURCE.concat("/" +carro.getId().toString())),
                jsonBody
        )
                )
                        .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("cor").value("Azul"));
    }

    @Test
    @DisplayName("Testa se o endPoit retorna por ID")
    public void deveObterCarroPorId() throws Exception {
        Carro carro = cadastraCarroNoDb();

        mvc.perform(
                        this.criaRequest(
                                MockMvcRequestBuilders.get(RESOURCE.concat("/" +carro.getId().toString())),
                                ""
                        )
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(carro.getId()));

    }

    @Test
    @DisplayName("Testa se o endPoit esta excluindo corretamente")
    public void deveApagarCarro() throws Exception {
        Carro carro = cadastraCarroNoDb();
        mvc.perform(
                this.criaRequest(
                        MockMvcRequestBuilders.delete(RESOURCE.concat("/" +carro.getId().toString())),
                        ""
                )
        ).andExpect(MockMvcResultMatchers.status().isNoContent());



    }

    private Carro cadastraCarroNoDb() {
        Carro carro = new Carro("Fiat", "Uno", 2015, "Flex", "Verde");
        return this.carroRepository.save(carro);
    }
}





