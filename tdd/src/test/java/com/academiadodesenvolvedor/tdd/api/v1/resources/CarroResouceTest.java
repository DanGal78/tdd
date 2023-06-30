package com.academiadodesenvolvedor.tdd.api.v1.resources;

import com.academiadodesenvolvedor.tdd.api.form.CreateCarroForm;
import com.academiadodesenvolvedor.tdd.api.v1.resouces.CarroResource;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@WebMvcTest(controllers = CarroResource.class)
public class CarroResourceTest {

    @Autowired
    private MockMvc mvc;
    static final String RESOURCE = "/api/v1/carros";
    static final MediaType JSONTYPE = MediaType.APPLICATION_JSON;

    @Test
    @DisplayName("Testa se o carro esta sendo cadastrada corretamente")
    public void deveCadastrarUmCarro() throws Exception{
        CreateCarroForm form = new CreateCarroForm("Ford","Ka", 2007, "Gasolina", "Branco");
        String bodyJson = new ObjectMapper().writeValueAsString(form);

        mvc.perform(this.criaRequest(MockMvcRequestBuilders.post(RESOURCE), bodyJson))
                        .andExpect(MockMvcResultMatchers.jsonPath("modelo").value("Ka"))
                        .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    public MockHttpServletRequestBuilder criaRequest(MockHttpServletRequestBuilder method, String content){
        return method.content(content).contentType(JSONTYPE).accept(JSONTYPE);

    }
}
