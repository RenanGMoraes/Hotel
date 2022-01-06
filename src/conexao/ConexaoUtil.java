/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoUtil {

    public Connection getConnection() throws ClassNotFoundException, SQLException{
        return DriverManager.getConnection("jdbc:mysql://localhost/hotel?serverTimezone=UTC","root","abcd");
       
    }
    
    
}
