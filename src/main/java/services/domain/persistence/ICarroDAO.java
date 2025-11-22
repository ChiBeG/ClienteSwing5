package services.domain.persistence;

public interface ICarroDAO extends IDAO<CarroDTO> {
    CarroDTO findByPlaca(String placa);
}
