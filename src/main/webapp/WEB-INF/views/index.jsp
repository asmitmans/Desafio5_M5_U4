<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Desafio Proveedores2</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">
<link
	href="//cdn.datatables.net/2.0.8/css/dataTables.dataTables.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
	integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
	crossorigin="anonymous" referrerpolicy="no-referrer">
<link href="${pageContext.request.contextPath}/css/styles.css"
	rel="stylesheet">
</head>

<body class="bg-dark text-light">

	<div class="container">
		<h2 class="text-center text-light mt-4">Proveedores</h2>

		<c:if test="${not empty sessionScope.message}">
			<div
				class="alert ${fn:contains(sessionScope.message, 'ERROR') ? 'alert-danger' : 'alert-success'} mt-3">${sessionScope.message}</div>
			<c:remove var="message" scope="session" />
		</c:if>

		<table class="table table-dark table-striped" id="tblProveedor">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th>RUT</th>
					<th>Dirección</th>
					<th>Correo</th>
					<th>Telefono</th>
					<th>Contacto</th>
					<th>Telefono Contacto</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="p" items="${proveedores}">
					<tr>
						<td><c:out value="${p.getId()}" /></td>
						<td><c:out value="${p.getNombre()}" /></td>
						<td><c:out value="${p.getRut()}" /></td>
						<td><c:out value="${p.getDireccion()}" /></td>
						<td><c:out value="${p.getCorreo()}" /></td>
						<td><c:out value="${p.getTelefono()}" /></td>
						<td><c:out value="${p.getContacto()}" /></td>
						<td><c:out value="${p.getTelefonoContacto()}" /></td>
						<td>
							<div class="d-flex">
								<a
									href="${pageContext.request.contextPath}/?view=edit&id=${p.getId()}"
									class="btn btn-outline-light btn-sm me-2">Editar</a>
								<form action="${pageContext.request.contextPath}/" method="post"
									style="display: inline;"
									onsubmit="return confirm('¿Estás seguro de que deseas eliminar este proveedor?');">
									<input type="hidden" name="action" value="delete"> <input
										type="hidden" name="id" value="${p.getId()}">
									<button type="submit" class="btn btn-outline-light btn-sm">Eliminar</button>
								</form>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="text-center pt-3">
			<a href="${pageContext.request.contextPath}/?view=create"
				class="btn btn-outline-light btn-lg">Crear Proveedor</a>
		</div>

	</div>


	<script src="https://code.jquery.com/jquery-3.7.1.min.js"
		integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
		crossorigin="anonymous"></script>
	<script src="https://cdn.datatables.net/2.0.8/js/dataTables.min.js"></script>

	<script>
	$(document).ready(() => {
		$('#tblProveedor').DataTable({
			"order": [[1, 'asc']] // Ordenar por la segunda columna (Nombre) de manera ascendente
		});
	});
	</script>

</body>
</html>