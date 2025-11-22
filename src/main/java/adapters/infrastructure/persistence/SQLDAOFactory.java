package adapters.infrastructure.persistence;

import services.domain.persistence.DAOType;
import services.domain.persistence.IDAO;
import services.domain.persistence.IDAOFactory;

public class SQLDAOFactory implements IDAOFactory {
    @Override
    public <T extends IDAO<?>> T create(DAOType type) {
        return switch(type) {
            case CLIENTE -> (T) new ClienteSQLDAO();
            case CARRO -> (T) new CarroSQLDAO();
        };
    }
}
