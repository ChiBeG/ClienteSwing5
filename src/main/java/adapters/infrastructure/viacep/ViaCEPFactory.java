package adapters.infrastructure.viacep;

import services.application.cep.IBuscaCEP;
import services.application.cep.ServicoCEPFactory;

/**
 * Fábrica do serviço ViaCEP
 */
public class ViaCEPFactory implements ServicoCEPFactory {

	@Override
	public IBuscaCEP cria() {
		return new BuscaCEPViaCEP();
	}

}
