package adapters.infrastructure.persistence;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;

/**
 * Classe singleton que retorna uma conexão JDBC
 */
public class DBConnection {

    private static String url;
    private static Connection conn = null;

    /**
     * Cria ou retorna uma conexão criada previamente
     *
     * @return Conexão JDBC
     * @throws RuntimeException Em caso de problemas na conexão
     */
    public static synchronized Connection get()  {

        try {
            if (conn == null || conn.isClosed()) {
                var dataSource = new SQLiteDataSource();
                dataSource.setUrl(url);
                conn = dataSource.getConnection();
            }

            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Define a URL de conexão
     *
     * @param url URL de conexão
     */
    public static void setUrl(String url) {
        DBConnection.url = url;
    }
    
    /**
     * Fecha a conexão JDBC
     */
    public static synchronized void close() {
        try {
            if (! conn.isClosed())
                conn.close();
        }
        catch(Exception ignored) {
        }
    }
}
