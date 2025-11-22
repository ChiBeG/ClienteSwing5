package services.application.uf;

import model.UF;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe que implementa o serviço de recuperação da lista de UFs
 */
public class UFService implements UFPort {

	/**
	 * Retorna um mapa com a UF e sua descrição
	 * 
	 * @return Mapa com a UF e sua descrição
	 */
	@Override
	public Map<String, String> getUFs() {
		var ufs = new HashMap<String, String>();
		
		for (UF uf : UF.values())
			ufs.put(uf.getSigla(), uf.getDescricao());
		
		return ufs;
	}

}
