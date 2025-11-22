package services.domain.persistence;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class RepositoryFactory {

    // IMPLEMENTAÇÃO 1 --------------------------------------------------------------

    // Armazena as instâncias de Repository em um Map. A chave vai ser o RepoType
    private static Map<RepoType, IRepository<?>> repos1 = new HashMap<>();

    // Cria o Repository com base no DAOType
    public static <T extends IRepository<?>> T create(RepoType type) {
        // Verifica se a instância já está no Map, usando o DAOType como chave
        var repo = repos1.get(type);

        if (repo == null) {
            // Se não estiver, cria uma nova instância e guarda no Map
            switch (type) {
                case CLIENTE -> repo = (T) new ClienteRepository();
                case CARRO -> repo = (T) new CarroRepository();
            };
            repos1.put(type, repo);
        }

        return (T) repo;
    }

    // IMPLEMENTAÇÃO 2 --------------------------------------------------------------

    // Armazena as instâncias de Repository em um Map. A chave vai ser o nome da classe.
    private static Map<String, IRepository<?>> repos = new HashMap<>();

    // Cria o Repository com base na classe desejada
    public static <T extends IRepository<?>> T create(Class<T> c) {
        try {
            // Verifica se a instância já está no Map, usando o nome da classe como chave
            var repo = repos.get(c.getName());

            if (repo == null) {
                // Se não estiver, cria uma nova instância e guarda no Map
                repo = c.getDeclaredConstructor().newInstance();
                repos.put(c.getName(), repo);
            }

            return (T) repo;
        } catch (InstantiationException|
                 IllegalAccessException|
                 InvocationTargetException|
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
