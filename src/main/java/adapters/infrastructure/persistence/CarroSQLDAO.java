package adapters.infrastructure.persistence;

import services.domain.persistence.CarroDTO;
import services.domain.persistence.ICarroDAO;

import java.util.List;

public class CarroSQLDAO implements ICarroDAO {
    @Override
    public CarroDTO findByPlaca(String placa) {
        return null;
    }

    @Override
    public void insert(CarroDTO dto) {

    }

    @Override
    public void update(CarroDTO dto) {

    }

    @Override
    public void delete(CarroDTO dto) {

    }

    @Override
    public List<CarroDTO> findAll() {
        return List.of();
    }
}
