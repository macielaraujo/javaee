/**
 * Script para confirmação de exclusão de contato
 */

 function confirmarExclusao(id){
	let confirma = confirm("Confirmar exclusão de contato?")
	if(confirma === true){
		//alert(id)
		window.location.href="delete?id="+id
	}
 }