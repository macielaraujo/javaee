<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.javaBeans" %>
<%@ page import="java.util.ArrayList" %>

<%
	@ SuppressWarnings("unchecked")
	ArrayList<javaBeans> lista = (ArrayList<javaBeans>) request.getAttribute("contatos");
	/*for(int i=0; i<lista.size(); i++){
		out.println(lista.get(i).getId());
		out.println(lista.get(i).getNome());
		out.println(lista.get(i).getFone());
		out.println(lista.get(i).getEmail());
	}*/

%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Agenda de Contatos</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="styleAgenda.css">
</head>
<body>
	<div id="principal">
		<h1>Agenda de Contatos</h1>
		<a href="cadastro.html" class="botaoGeral">Novo Contato</a>
		<a href="geraRelatorio" class="botaoGeral" style="background-color: var(--corLaranja);">Relatório</a>
		
		<table id="tabelaContatos">
			<thead>
				<tr>
					<th class="thId" style="min-width:20px">Id</th>
					<th style="min-width: 170px;">Nome</th>
					<th style="min-width: 155px;">Fone</th>
					<th>E-mail</th>
					<th style="min-width: 130px;">Opções</th>
				</tr>
			</thead>
			<tbody>
				<%for(int i=0; i<lista.size(); i++){ %>
					<tr>
						<td class="tdId"><%=lista.get(i).getId() %></td>
						<td><%=lista.get(i).getNome() %></td>
						<td><%=lista.get(i).getFone() %></td>
						<td><%=lista.get(i).getEmail() %></td>
						<td><a href="edita?id=<%=lista.get(i).getId() %>" ><img title="Editar Contato" style="margin: 0 10px;" alt="botão editar" src="imagens/lapis32.png"></a>
						<a href="javascript:confirmarExclusao(<%=lista.get(i).getId() %>)" ><img title="Deletar Contato" style="margin: 0 10px;" alt="botão deletar" src="imagens/delete32.png"></a></td>
						
					</tr>
				<%} %>
			</tbody>
		
		</table>
		
	</div>
	<script src="scripts/confirmaExclusao.js"></script>
</body>
</html>