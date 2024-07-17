<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="es">
<html>
<head>
<meta charset="UTF-8">
<title>Desafio Proveedores2</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
	integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
	crossorigin="anonymous" referrerpolicy="no-referrer">
<link href="${pageContext.request.contextPath}/css/styles.css"
	rel="stylesheet">
</head>
<body class="bg-dark text-light">

	<div class="container">
		<h2 class="text-center text-light mt-4">Editar Registro</h2>

		<c:if test="${not empty sessionScope.message}">
			<div
				class="alert ${fn:contains(sessionScope.message, 'ERROR') ? 'alert-danger' : 'alert-success'} mt-3">${sessionScope.message}</div>
			<c:remove var="message" scope="session" />
		</c:if>

		<form name="formulario" class="form" method="post"
			action="${pageContext.request.contextPath}/?action=update">
			<div class="row mb-3 d-flex justify-content-center">
				<div class="col-md-6">
					<input type="hidden" name="id" value="${proveedor.getId()}">
					<div class="mb-3">
						<label for="nombre" class="form-label">Nombre</label> <input
							type="text" class="form-control bg-dark text-light" id="nombre"
							name="nombre" required pattern="[A-Za-z0-9\sáéíóúÁÉÍÓÚñÑ]+"
							title="Debe contener solo letras, números y espacios"
							value="${proveedor.getNombre()}">
					</div>
					<div class="mb-3">
						<label for="rut" class="form-label">RUT</label> <input type="text"
							class="form-control bg-dark text-light" id="rut" name="rut"
							required pattern="[0-9\-]+"
							title="Debe contener solo números y guiones" value="${proveedor.getRut()}">
					</div>
					<div class="mb-3">
						<label for="direccion" class="form-label">Dirección</label> <input
							type="text" class="form-control bg-dark text-light"
							id="direccion" name="direccion" required pattern="[A-Za-z0-9\sáéíóúÁÉÍÓÚñÑ]+"
							title="Debe contener solo letras, números y espacios"
							value="${proveedor.getDireccion()}">
					</div>
					<div class="mb-3">
						<label for="correo" class="form-label">Correo</label> <input
							type="email" class="form-control bg-dark text-light" id="correo"
							name="correo" required
							title="Debe ser un correo electrónico válido" value="${proveedor.getCorreo()}">
					</div>
					<div class="mb-3">
						<label for="telefono" class="form-label">Teléfono</label> <input
							type="text" class="form-control bg-dark text-light" id="telefono"
							name="telefono" required pattern="[0-9\s]+"
							title="Debe contener solo números y espacios" value="${proveedor.getTelefono()}">
					</div>
					<div class="mb-3">
						<label for="contacto" class="form-label">Contacto</label> <input
							type="text" class="form-control bg-dark text-light" id="contacto"
							name="contacto" required pattern="[A-Za-z0-9\sáéíóúÁÉÍÓÚñÑ]+"
							title="Debe contener solo letras, números y espacios"
							value="${proveedor.getContacto()}">
					</div>
					<div class="mb-3">
						<label for="telefono_contacto" class="form-label">Teléfono
							Contacto</label> <input type="text"
							class="form-control bg-dark text-light" id="telefono_contacto"
							name="telefono_contacto" required pattern="[0-9\s]+"
							title="Debe contener solo números y espacios"
							value="${proveedor.getTelefonoContacto()}">
					</div>
					
					<div class="my-4 text-center pt-2">
    					<div class="d-grid gap-2 d-md-flex justify-content-md-between">	
							<a href="${pageContext.request.contextPath}/"
							class="btn btn-outline-secondary btn-lg">Cancelar</a>
							<button type="submit" class="btn btn-outline-light btn-lg ">Guardar</button>
						</div>
					</div>
					
				</div>
			</div>
			
		</form>
		
	</div>

</body>
</html>