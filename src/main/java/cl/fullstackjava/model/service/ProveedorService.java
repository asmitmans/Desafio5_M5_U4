package cl.fullstackjava.model.service;

import java.util.List;

import cl.fullstackjava.model.dto.Proveedor;

public interface ProveedorService {
	
	Response<Void> create(Proveedor proveedor);
	Response<Proveedor> read(int id);
	Response<List<Proveedor>> read();
	Response<Void> update(Proveedor proveedor);
	Response<Void> delete(int id);
	
}
