package connections;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
    private static String url = "jdbc:postgresql://localhost:5432/Modulo22?autoReconnect=true";
    private static String usuario = "postgres";
    private static String senha = "postgre123";
    private static Connection connection = null;

    static {
        conectar(); // cria a conexão quando apenas a classe é chamada e não instanciada
    }

    public SingleConnection() {
        conectar(); // quando instanciar a classe irá se conectar
    }

    private static void conectar(){
        try {

            if (connection == null){

                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url,usuario,senha);
                connection.setAutoCommit(false);

            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return connection;
    }
}
