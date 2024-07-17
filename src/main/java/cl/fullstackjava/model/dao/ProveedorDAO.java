package cl.fullstackjava.model.dao;

import java.util.List;

import cl.fullstackjava.model.dto.Proveedor;

public interface ProveedorDAO {
	boolean create(Proveedor proveedor) throws Exception;
	Proveedor read(int id) throws Exception;
	List<Proveedor> read() throws Exception;
	boolean update(Proveedor proveedor) throws Exception;
	boolean delete(int id) throws Exception;
}