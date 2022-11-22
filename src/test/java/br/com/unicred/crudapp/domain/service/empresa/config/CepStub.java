package br.com.unicred.crudapp.domain.service.empresa.config;

import br.com.unicred.crudapp.infraestructure.client.ViaCepResponse;
import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@Component
public class CepStub {

    //stub é uma resposta pronta que eu configuro para alguma chamada http

    public void consultaCepSucesso() throws IOException {
        final int status = 200;
        Gson gson = new Gson();
        ViaCepResponse viaCepResponse = new ViaCepResponse();
        viaCepResponse.setCep("91350300");

        stubFor(get(urlEqualTo("/91350300/json/"))
                .willReturn(aResponse()
                        .withStatus(status)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(gson.toJson(viaCepResponse))));
    }

    public void consultaCepInexistente() throws IOException {
        final int status = 200;
        Gson gson = new Gson();
        ViaCepResponse viaCepResponse = new ViaCepResponse();
        viaCepResponse.setCep(null);

        stubFor(get(urlEqualTo("/00000000/json/"))
                .willReturn(aResponse()
                        .withStatus(status)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(gson.toJson(viaCepResponse))));
    }
}
