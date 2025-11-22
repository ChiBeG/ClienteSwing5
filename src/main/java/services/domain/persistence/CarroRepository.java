package services.domain.persistence;

import model.Carro;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarroRepository extends Repository<Carro> {

    private final ICarroDAO dao = DAOFactory.create(DAOType.CARRO);

    public Carro findByPlaca(String placa) {
        var dto = dao.findByPlaca(placa);
        Carro carro = null;

        if (dto != null) {
            carro = Carro.of(dto.placa, dto.modelo, dto.anoFabricacao, dto.km);
            carro.setId(dto.id);
        }

        return carro;
    }

    @Override
    public void add(Carro entity) {
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID());
            dao.insert(CarroDTO.fromEntity(entity));
        }
        else {
            dao.update(CarroDTO.fromEntity(entity));
        }
    }

    @Override
    public void remove(Carro entity) {
        if (entity.getId() != null) {
            dao.delete(CarroDTO.fromEntity(entity));
            entity.setId(null);
        }
    }

    @Override
    public List<Carro> findAll() {
        List<Carro> carros = new ArrayList<>();

        // Cria um carro para cada DTO
        for (var dto : dao.findAll()) {
            var carro = Carro.of(dto.placa, dto.modelo, dto.anoFabricacao, dto.km);
            carro.setId(dto.id);
            carros.add(carro);
        }

        return carros;
    }
}
