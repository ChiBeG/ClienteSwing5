package services.domain.persistence;

import model.Carro;

import java.util.UUID;

/**
 * Classe usada para trafegar dados entre a aplicação e o BD
 */
public class CarroDTO extends EntityDTO {
    public final String placa;
    public final String modelo;
    public final Integer anoFabricacao;
    public final Integer km;

    public CarroDTO(UUID id, String placa, String modelo, Integer anoFabricacao, Integer km) {
        super(id);
        this.placa = placa;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.km = km;
    }

    /**
     * Cria o DTO a partir do carro
     * @param carro Carro
     * @return DTO do carro
     */
    public static CarroDTO fromEntity(Carro carro) {
        return new CarroDTO(carro.getId(),
                            carro.getPlaca(),
                            carro.getModelo(),
                            carro.getAnoFabricacao(),
                            carro.getKm());
    }
}
