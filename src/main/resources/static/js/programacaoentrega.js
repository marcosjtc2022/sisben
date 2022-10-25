  
document.querySelector('#btn-salvar').addEventListener('click', async () => {
   //alert("entrou");
   
    var id = $("#id").val();    
		         var matriculaColaborador = $("#matriculaColaborador").val();
		         
		         //alert(" matricula = " + matriculaColaborador);
		         
		         let dia = "";
		         let diaDaSemana = "";
		         let diaEspecial = "";
		         let localEntrega = "";
		         let tabelaProgramacaoEntregaArray = [];
		         let tabelaProgramacaoEntrega ;
		         
		         //let numlinhas = tabelaProgramacao.rows;
		         
		         var tabelaProgramacao = document.querySelector("#tabelaProgramacao tbody");
		         
		         for (let row of tabelaProgramacao.rows) 
				{
					
					let colunas = row.cells;
					
					//alert("antes");
				//	if (colunas[0].innerText != null){
				//		dia 	= colunas[0].innerText;
				//	}
					
					tabelaProgramacaoEntregaArray.push(matriculaColaborador + "=" +
					                   "BROTAS" + "=" + "CABULA" + "=" +  
			                           "2022-10-29" + "=" +
			                           "2022-10-29" + "=" + 
			                           "1=1=1=4=0=1");
					
					//alert("depois");
					//localEntrega = colunas[3].innerText;
					//localEntrega = "";
					
					//alert("dia = " + dia );
				 	
				} 
				
				 tabelaProgramacaoEntrega = tabelaProgramacaoEntregaArray.toString();
				
				//document.getElementById("tabelaprogramacaoEntregahidden").value = tabelaProgramacaoEntrega;
				
				  $.ajax({
				  method: "POST",
				  url: "programacoes-entregas/salva-lotes",
				  data: JSON.stringify({matriculaColaborador: matriculaColaborador , tabelaProgramacaoEntrega: tabelaProgramacaoEntrega, idUsuario:1   }),
				  contentType: "application/json; charset=utf-8",
				  success: function(response){
					alert("Salvo com sucesso!");
				  } 
				}).fail(function( xhr, status, errorThrown ) {
				    alert( "Erro ao salvar: " + xhr.responseText );
				});
   
});