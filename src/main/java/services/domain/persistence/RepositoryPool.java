package services.domain.persistence;

import util.pool.Pool;

public class RepositoryPool {
    private static Pool<IRepository<?>> instance;

    private RepositoryPool() {}

    public static void setup(int maxIdle, int reapInterval) {
        if (instance == null) {
            instance = new Pool<>(maxIdle, reapInterval);
        }
    }

    public static <T extends IRepository<?>> T acquire(Class<T> c) {
        if (instance == null)
            return null;

        return (T) instance.acquire(c);
    }

    public static synchronized <T extends IRepository<?>> void release(T obj) {
        if (instance == null)
            return;

        instance.release(obj);
    }
}
