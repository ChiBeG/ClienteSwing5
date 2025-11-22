package util.pool;

public class PoolReaper extends Thread {
    /**
     * Pool que será limpo
     */
    private final IPool pool;

    /**
     * Intervalo de limpeza
     */
    private final int reapInterval;

    PoolReaper(IPool pool, int reapInterval) {
        this.pool = pool;
        this.reapInterval = reapInterval;
    }

    /**
     * Método que será executado em uma thread separada e que
     * fará a chamada para a limpeza do pool
     */
    public void run() {
        for(;;) {
            try {
                sleep(reapInterval);
            } catch( InterruptedException ignored) { }
            pool.reapObjects();
        }
    }
}
