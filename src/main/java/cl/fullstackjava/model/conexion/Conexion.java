package cl.fullstackjava.model.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private static Connection con = null;
	
	private Conexion() {
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/desafioproveedores",
					"postgres", "passpsql");
			} catch(ClassNotFoundException | SQLException ex) {
				System.out.println("Error al conectar con la BD: " + ex.getMessage());
				}
		}
	
	public static Connection getCon() {
		try {
			if (con == null || con.isClosed()) {
				new Conexion();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}

}