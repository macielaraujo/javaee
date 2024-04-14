<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Contato</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="styleAgenda.css">
</head>
<body class="bodyCadastro">

	<div id="cadastro">
		<div id="caixaForm">
			<h1>Editar Contato</h1>
			<form name="formContato" action="atualizaContato">
				<table>
					<tr>
						<td><input type="text" name="id" value="<%out.print(request.getAttribute("id")); %>" readonly></td>
					</tr>
					<tr>
						<td><input type="text" name="nome" value="<%out.print(request.getAttribute("nome"));%>"></td>
					</tr>
					<tr>
						<td><input type="text" name="fone" value="<%out.print(request.getAttribute("fone"));%>"></td>
					</tr>
					<tr>
						<td><input type="email" name="email" value="<%out.print(request.getAttribute("email"));%>"></td>
					</tr>
				</table>
				<input type="button" value="Salvar" class="botaoActionForm"
					onclick="validar()">
				
			</form>
			<script src="scripts/scriptValidar.js"></script>
		</div>
	</div>

</body>
</html>