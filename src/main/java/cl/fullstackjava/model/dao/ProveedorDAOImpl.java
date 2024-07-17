package cl.fullstackjava.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cl.fullstackjava.model.conexion.Conexion;
import cl.fullstackjava.model.dto.Proveedor;

// Ejecutar query para la inserción y obtención de 
//   proveedores en clase de acceso a datos.
public class ProveedorDAOImpl implements ProveedorDAO {

	@Override
	public boolean create(Proveedor proveedor) throws Exception {
		
		String sql = "INSERT INTO proveedores (nombre, rut, direccion, correo, telefono, contacto, telefono_contacto) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try (Connection c = Conexion.getCon();
			 PreparedStatement ps = c.prepareStatement(sql)) {
			
			ps.setString(1, proveedor.getNombre());
			ps.setString(2, proveedor.getRut());
			ps.setString(3, proveedor.getDireccion());
			ps.setString(4, proveedor.getCorreo());
			ps.setString(5, proveedor.getTelefono());
			ps.setString(6, proveedor.getContacto());
			ps.setString(7, proveedor.getTelefonoContacto());
			
			int rowAffected = ps.executeUpdate();
			
			return rowAffected > 0;
		
		} catch (SQLException e) {
			throw new Exception("Error en la base de datos: " + e.getMessage(), e);
		}
	}

	
	@Override
	public Proveedor read(int id) throws Exception {
		
		String sql = "SELECT id, nombre, rut, direccion, correo, telefono, contacto, telefono_contacto FROM proveedores WHERE id = ?";
		
		try (Connection conn = Conexion.getCon();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, id);
			
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new Proveedor(
							rs.getInt("id"),
							rs.getString("nombre"),
							rs.getString("rut"),
							rs.getString("direccion"),
							rs.getString("correo"),
							rs.getString("telefono"),
							rs.getString("contacto"),
							rs.getString("telefono_contacto")
							);
				} else {
					throw new Exception("Proveedor no encontrado");
				}
			}
			
		} catch (SQLException e) {
			throw new Exception("Error en la base de datos: " + e.getMessage(), e);
		}
	}
	
	@Override
	public List<Proveedor> read() throws Exception {

		String sql = "SELECT id,nombre,rut,direccion,correo,telefono,contacto,telefono_contacto FROM proveedores ORDER BY nombre ASC;";
		List<Proveedor> proveedores = new ArrayList<>();
		
		try (Connection c = Conexion.getCon();
			 Statement s = c.createStatement();
			 ResultSet rs = s.executeQuery(sql)) {
			
			while (rs.next()) {
				proveedores.add(new Proveedor(
						rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("rut"),
						rs.getString("direccion"),
						rs.getString("correo"),
						rs.getString("telefono"),
						rs.getString("contacto"),
						rs.getString("telefono_contacto")
				));
			}			
		} catch (SQLException e) {
			throw new Exception("Error en la base de datos: " + e.getMessage(), e);
		}
		return proveedores;
	}

	@Override
	public boolean update(Proveedor proveedor) throws Exception {
		
		String sql = "UPDATE proveedores SET nombre = ?, rut = ?, direccion = ?, correo = ?, telefono = ?, contacto = ?, telefono_contacto = ? WHERE id = ?";
		
		try (Connection c = Conexion.getCon();
			 PreparedStatement ps = c.prepareStatement(sql)) {
			
			ps.setString(1, proveedor.getNombre());
			ps.setString(2, proveedor.getRut());
			ps.setString(3, proveedor.getDireccion());
			ps.setString(4, proveedor.getCorreo());
			ps.setString(5, proveedor.getTelefono());
			ps.setString(6, proveedor.getContacto());
			ps.setString(7, proveedor.getTelefonoContacto());
			ps.setInt(8, proveedor.getId());
			
			int rowAffected = ps.executeUpdate();			
			return rowAffected > 0;
			
		} catch (SQLException e) {
			throw new Exception("Error en la base de datos: " + e.getMessage(), e);
		}
	}

	@Override
	public boolean delete(int id) throws Exception {
		
		String sql = "DELETE FROM proveedores WHERE id = ?";
		
		try (Connection c = Conexion.getCon();
			 PreparedStatement ps = c.prepareStatement(sql)) {
			
			ps.setInt(1, id);
			
			int rowAffected = ps.executeUpdate();			
			return rowAffected > 0;
			
		} catch (SQLException e) {
			throw new Exception("Error en la base de datos: " + e.getMessage(), e);
		}
	}
		
		

}
