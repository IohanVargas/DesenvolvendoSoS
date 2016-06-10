package br.com.dao.config;
/*
 * @author DiogenesDias
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOconexao {

    public static Connection connection = null;
    public static String strDriver = "com.mysql.jdbc.Driver";

    public static Connection getConnection() {

        try {
            Class.forName(strDriver);
            connection = DriverManager.getConnection("jdbc:mysql://localhost/bancoSoS,root,root");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DAOconexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}
