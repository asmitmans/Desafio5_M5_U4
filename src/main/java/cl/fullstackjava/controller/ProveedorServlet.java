package cl.fullstackjava.controller;

import cl.fullstackjava.model.dto.Proveedor;
import cl.fullstackjava.model.service.ProveedorService;
import cl.fullstackjava.model.service.ProveedorServiceImpl;
import cl.fullstackjava.model.service.Response;
import cl.fullstackjava.model.dao.ProveedorDAO;
import cl.fullstackjava.model.dao.ProveedorDAOImpl;



import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/")
public class ProveedorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProveedorService proveedorService;
	
	public ProveedorServlet() {
		super();
		this.proveedorService = new ProveedorServiceImpl(new ProveedorDAOImpl());
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		
		String view = request.getParameter("view");
		String srtId = request.getParameter("id");
		HttpSession session = request.getSession();
		
		if (view == null) {
			Response<List<Proveedor>> responseProveedores = proveedorService.read();
			if(responseProveedores.isSuccess()) {
				request.setAttribute("proveedores", responseProveedores.getData());
			} else {
				session.setAttribute("message", responseProveedores.getMessage());
			}
			request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);			
			return;
			
		} else if (view.equals("create")) {
			request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
			return;
			
		} else if (view.equals("edit")) {
			try {
		        int id = Integer.parseInt(srtId);
		        Response<Proveedor> responseProveedores = proveedorService.read(id);
		        if (responseProveedores.isSuccess()) {
		            request.setAttribute("proveedor", responseProveedores.getData());
		        } else {
		        	session.setAttribute("message", responseProveedores.getMessage());
		        }
		        request.getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(request, response);
		        return;
		    } catch (NumberFormatException e) {
		    	session.setAttribute("message", "ID de proveedor no válido");
		        response.sendRedirect(request.getContextPath() + "/");
		    } catch (Exception e) {
		        e.printStackTrace();
		        session.setAttribute("message", "Error al obtener el proveedor: " + e.getMessage());
		        response.sendRedirect(request.getContextPath() + "/");
		    }
		    return;
		}	
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String strId = request.getParameter("id");
		HttpSession session = request.getSession();
		
		
		try {
			if (action.equals("create")) {
				create(request, response);
				return;
				
			} else if (action.equals("update")) {
				update(request, response);
				return;
				
			} else if (action.equals("delete")) {
				try {
					int id = Integer.parseInt(strId);
					Response<Void> responseProveedor = proveedorService.delete(id);
					session.setAttribute("message", responseProveedor.getMessage());
					response.sendRedirect(request.getContextPath() + "/");
					return;
				} catch (NumberFormatException e) {
					session.setAttribute("message", "ID de proveedor no válido");
					response.sendRedirect(request.getContextPath() + "/");
				} catch (Exception e) {
					e.printStackTrace();
					session.setAttribute("message", "Error al eliminar el proveedor: " + e.getMessage());
					response.sendRedirect(request.getContextPath() + "/");
				}
				return;
				
			} else {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida");
				return;
			}
		} catch (IllegalArgumentException e) {
			request.setAttribute("error", e.getMessage());
			response.sendRedirect(request.getContextPath() + "/");
			return;
		}
	}
	
	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String nombre = request.getParameter("nombre");
		String rut = request.getParameter("rut");
		String direccion = request.getParameter("direccion");
		String correo = request.getParameter("correo");
		String telefono = request.getParameter("telefono");
		String contacto = request.getParameter("contacto");
		String telefono_contacto = request.getParameter("telefono_contacto");
		
		if (nombre == null || nombre.isEmpty() ||
			rut == null || rut.isEmpty() ||
			direccion == null || direccion.isEmpty() ||
			correo == null || correo.isEmpty() ||
			telefono == null || telefono.isEmpty() ||
			contacto == null || contacto.isEmpty() ||
			telefono_contacto == null || telefono_contacto.isEmpty()) {
			
			session.setAttribute("message", "Todos los campos son obligatorios.");
			request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
			return;
		}
		
		Proveedor proveedor = new Proveedor();
		proveedor.setNombre(nombre);
		proveedor.setRut(rut);
		proveedor.setDireccion(direccion);
		proveedor.setCorreo(correo);
		proveedor.setTelefono(telefono);
		proveedor.setContacto(contacto);
		proveedor.setTelefonoContacto(telefono_contacto);
		
		Response<Void> responseProveedor = proveedorService.create(proveedor);
		session.setAttribute("message", responseProveedor.getMessage());
		response.sendRedirect(request.getContextPath() + "/");			
		return;		
	}
	
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String strId = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String rut = request.getParameter("rut");
		String direccion = request.getParameter("direccion");
		String correo = request.getParameter("correo");
		String telefono = request.getParameter("telefono");
		String contacto = request.getParameter("contacto");
		String telefono_contacto = request.getParameter("telefono_contacto");
		
		if (strId == null || strId.isEmpty() ||
				nombre == null || nombre.isEmpty() ||
				rut == null || rut.isEmpty() ||
				direccion == null || direccion.isEmpty() ||
				correo == null || correo.isEmpty() ||
				telefono == null || telefono.isEmpty() ||
				contacto == null || contacto.isEmpty() ||
				telefono_contacto == null || telefono_contacto.isEmpty()) {
			
			session.setAttribute("message", "Todos los campos son obligatorios.");
			request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
			return;
		}
		
		try {
			int id = Integer.parseInt(strId);
			
			Proveedor proveedor = new Proveedor();
			proveedor.setId(id);
			proveedor.setNombre(nombre);
			proveedor.setRut(rut);
			proveedor.setDireccion(direccion);
			proveedor.setCorreo(correo);
			proveedor.setTelefono(telefono);
			proveedor.setContacto(contacto);
			proveedor.setTelefonoContacto(telefono_contacto);
			
			Response<Void> responseProveedor = proveedorService.update(proveedor);
			session.setAttribute("message", responseProveedor.getMessage());
			response.sendRedirect(request.getContextPath() + "/");
		} catch (NumberFormatException e) {
			session.setAttribute("message", "ID de proveedor no válido.");
			request.getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("message", "Error al actualizar el proveedor: " + e.getMessage());
			request.getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(request, response);
		}
		
	}
	
	
}
