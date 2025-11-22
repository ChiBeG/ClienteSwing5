package model;

import java.util.stream.Stream;

/**
 * Unidades da federação e suas descrições
 */
public enum UF {

	RJ("RJ", "Rio de Janeiro"),
	SP("SP", "São Paulo"), 
	MG("MG", "Minas Gerais"),
	ES("ES", "Espirito Santo"),
	PR("PR", "Paraná"),
	SC("SC", "Santa Catarina"),
	RS("RS", "Rio Grande do Sul"),
	GO("GO", "Goiás"),
	MT("MT", "Mato Grosso"),
	MS("MS", "Mato Grosso do Sul"),
	BA("BA", "Bahia"),
	SE("SE", "Sergipe"),
	AL("AL", "Alagoas"),
	PE("PE", "Pernambuco"),
	RN("RN", "Rio Grande do Norte"),
	CE("CE", "Ceara"),
	MA("MA", "Maranhão"),
	PI("PI", "Piauí"),
	TO("TO", "Tocantins"),
	PA("PA", "Pará"),
	AM("AM", "Amazonas"),
	RR("RR", "Roraima"),
	RO("RO", "Rondônia"),
	AC("AC", "Acre"),
	DF("DF", "Distrito Federal");
	
	private final String sigla;
	private final String descricao;
	
	private UF(String sigla, String descricao) {
		this.sigla = sigla;
		this.descricao = descricao;
	}
	
	public String getSigla() {
		return sigla;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * Verifica se uma UF é válida
	 * 
	 * @param uf Sigla
	 * @return Verdadeiro, se UF for válida; ou Falso, caso contrário
	 */
	public static boolean isValid(String uf) {
		return Stream.of(values()).anyMatch(e -> e.sigla.equals(uf));
	}

    /**
     * Retorna uma UF com base na sua descrição
     *
     * @param descricao Descrição ou nome da UF
     * @return UF ou null, se não existir
     */
    public static UF getByDescricao(String descricao) {
        return  Stream.of(values()).filter(e -> e.descricao.equals(descricao)).findFirst().orElse(null);
    }

    /**
     * Retorna uma UF com base na sua sigla
     *
     * @param sigla Sigla da UF
     * @return UF ou null, se não existir
     */
    public static UF getBySigla(String sigla) {
        return  Stream.of(values()).filter(e -> e.sigla.equals(sigla)).findFirst().orElse(null);
    }
}
