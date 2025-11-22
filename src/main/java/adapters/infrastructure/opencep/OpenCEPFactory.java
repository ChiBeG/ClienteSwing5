package adapters.infrastructure.opencep;

import services.application.cep.IBuscaCEP;
import services.application.cep.ServicoCEPFactory;

/**
 * Fábrica do serviço OpenCEP
 */
public class OpenCEPFactory implements ServicoCEPFactory {

	@Override
	public IBuscaCEP cria() {
		return new BuscaCEPOpenCEP();
	}

}
