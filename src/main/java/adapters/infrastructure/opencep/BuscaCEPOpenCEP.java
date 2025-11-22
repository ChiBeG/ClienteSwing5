package adapters.infrastructure.opencep;

import adapters.infrastructure.viacep.DadosViaCEP;
import com.google.gson.Gson;
import services.application.RequestResult;
import services.application.cep.DadosEndereco;
import services.application.cep.IBuscaCEP;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * Essa classe implementa o serviço de busca de endereços por CEP OpenCEP
 */
public class BuscaCEPOpenCEP implements IBuscaCEP {

	@Override
	public RequestResult<DadosEndereco> buscaEnderecoPorCEP(Integer cep) {
		try {
			var uri = "https://opencep.com/v1/"+cep.toString();

			// Cria um request com GET e timeout de 3 segundos
			HttpRequest request = HttpRequest.newBuilder()
					  .uri(new URI(uri))
					  .GET()
					  .timeout(Duration.of(3, ChronoUnit.SECONDS))
					  .build();
			
			HttpClient client = HttpClient.newHttpClient();
			
			// Faz a requisição
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			
			// 200 - CEP Ok
						if (response.statusCode() == 200) {
							
							// Converte o JSON da resposta para um objeto
							Gson gson = new Gson();
							var dados = gson.fromJson(response.body(), DadosViaCEP.class);
							
				
							// Converte o objeto da resposta para o objeto esperado
							// Se objeto tem propriedade "erro", então não encontrou o CEP
							return RequestResult.success(new DadosEndereco(dados.logradouro(), 
					                                 dados.bairro(), 
					                                 dados.localidade(), 
					                                 dados.uf()));
						}
						// 404 - CEP não encontrado
						else if (response.statusCode() == 404) {
							return RequestResult.failure();
						}
						else
							// Qualquer outro erro -> problemas no serviço
							return null;
		} catch (URISyntaxException|IOException|InterruptedException ignored) {
            return null;
        }
	}
	
}
