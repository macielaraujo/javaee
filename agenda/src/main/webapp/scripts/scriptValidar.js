/**
 * Script para validação 
 */

 function validar(){
	let nome = formContato.nome.value
	let fone = formContato.fone.value
	
	if(nome===""){
		alert("Campo obrigatório, por favor preencha todos os dados!");
		formContato.nome.focus();
		return false;
	}else if(fone===""){
		alert("Campo obrigatório, por favor preencha todos os dados!");
		formContato.fone.focus();
		return false;
	}else{
		document.forms["formContato"].submit();
	}
 }
 
