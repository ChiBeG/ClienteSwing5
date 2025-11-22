package services.domain.persistence;

public class DAOFactory {

    private static IDAOFactory factory;

    public static void register(IDAOFactory factory) {
        DAOFactory.factory = factory;
    }

    public static <T extends IDAO<?>> T create(DAOType type) {
        if (factory == null) {
            throw new RuntimeException("Fábrica de DAO não definida");
        }

        return factory.create(type);
    }
}
