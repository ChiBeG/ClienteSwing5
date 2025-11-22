package util.pool;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

public class Pool<T> implements IPool {
    private final int maxIdle;
    private final LinkedList<PooledObject<T>> pool = new LinkedList<>();

    public Pool(int maxIdle, int reapInterval) {
        this.maxIdle = maxIdle * 1000;

        var reaper = new PoolReaper(this, reapInterval * 1000);

        reaper.start();
    }

    public T acquire(Class<? extends T> c) {
        synchronized (pool) {
            if (!pool.isEmpty()) {
                return pool.removeLast().actual;
            }
        }

        try {
            return c.getDeclaredConstructor().newInstance();
        } catch (InstantiationException|IllegalAccessException|InvocationTargetException|NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void release(T obj) {
        for (var p : pool)
            if (p.actual == obj)
                return;

        pool.addLast(new PooledObject<>(System.currentTimeMillis() + maxIdle, obj));
    }

    @Override
    public void reapObjects() {
        long currentTime = System.currentTimeMillis();

        synchronized (pool) {
            pool.removeIf(p -> p.releaseTime < currentTime);
        }
    }
}
