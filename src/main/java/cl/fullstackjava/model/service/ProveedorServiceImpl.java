package cl.fullstackjava.model.service;

import java.util.List;

import cl.fullstackjava.model.dao.ProveedorDAO;
import cl.fullstackjava.model.dto.Proveedor;
import cl.fullstackjava.model.service.Response;

public class ProveedorServiceImpl implements ProveedorService {
	
	private ProveedorDAO proveedorDAO;
	
	public ProveedorServiceImpl(ProveedorDAO proveedorDAO) {
		this.proveedorDAO = proveedorDAO;
		
	}

	@Override
	public Response<Void> create(Proveedor proveedor) {
		try {
			boolean success = proveedorDAO.create(proveedor);
			if (success) {
				return new Response<>(true, "Proveedor creado exitosamente", null);
			} else {
				return new Response<>(false, "No se insertaron datos", null);
			}
		} catch (Exception e) {
			return new Response<>(false, "Error al crear el proveedor: " + e.getMessage(), null);
		}
	}

	@Override
	public Response<Proveedor> read(int id) {
		try {
			Proveedor proveedor = proveedorDAO.read(id);
			return new Response<>(true, "Proveedor encontrado", proveedor);
		} catch (Exception e) {
			return new Response<>(false, "Error al buscar el proveedor: " + e.getMessage(), null);
		}
	}

	@Override
	public Response<List<Proveedor>> read() {
		try {
			List<Proveedor> proveedores = proveedorDAO.read();
			return new Response<>(true, "Proveedores encontrado", proveedores);
		} catch (Exception e) {
			return new Response<>(false, "Error al obtener proveedores: " + e.getMessage(), null);
		}
	}
	
	@Override
	public Response<Void> update(Proveedor proveedor) {
		try {
			boolean success = proveedorDAO.update(proveedor);
			if (success) {
				return new Response<>(true, "Proveedor actualizado exitosamente", null);
			} else {
				return new Response<>(false, "No se actualizo el proveedor", null);
			}
		} catch (Exception e) {
			return new Response<>(false, "Error al actualizar el proveedor: " + e.getMessage(), null);
		}
	}
	

	@Override
	public Response<Void> delete(int id) {
		try {
			boolean success = proveedorDAO.delete(id);
			if (success) {
				return new Response<>(true, "Proveedor eliminado exitosamente", null);
			} else {
				return new Response<>(false, "No se eliminó el proveedor", null);
			}
		} catch (Exception e) {
			return new Response<>(false, "Error al eliminar el proveedor: " + e.getMessage(), null);
		}
	}

}
