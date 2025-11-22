package util.pool;

public class PooledObject<T> {
    public final long releaseTime;
    public final T actual;

    public PooledObject(long releaseTime, T actual) {
        this.releaseTime = releaseTime;
        this.actual = actual;
    }
}
