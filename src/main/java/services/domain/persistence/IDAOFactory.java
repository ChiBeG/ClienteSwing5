package services.domain.persistence;

public interface IDAOFactory {
    <T extends IDAO<?>> T create(DAOType type);
}