
package healthyclinics.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private Connection conn;

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    public Connection conectar() {
        
        conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Carrega o drive JDBC
            conn = DriverManager.getConnection("jdbc:mysql://localhost/healthyclinics_db", "root", "databaseDeveloper1001"); // Conecta com o banco de dados
            System.out.println("Banco de dados conectado.");
            
        }  catch (ClassNotFoundException ex) {
            System.out.println("Erro: Driver JDBC não encontrado!");
            
        } catch (SQLException ex) {
            System.out.println("Erro: Não foi possível se conectar com o banco de dados!");
        }
        return conn;
        
    }
    
    
    public void desconectar() {
        
        try {
            if(conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Banco de dados desconectado.");
            }
        } catch (SQLException ex) {
            System.out.println("Erro: Não foi possível desconectar do banco de dados!");
        }
        
    }
        
    
    
}
